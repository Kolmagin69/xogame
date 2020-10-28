package learning.java.game.dao;

import learning.java.game.model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class PlayersDao implements Dao<Player, UUID> {

    public static final Logger logger = LoggerFactory.getLogger(GamesDao.class);

    @Autowired
    private DataConnection dataConnection;

    @Override
    public UUID create(Player player) {
        final UUID id = player.getId();
        final String name = player.getName();
        return execute(SQLPlayer.INSERT, statement -> {
            statement.setObject(1, id);
            statement.setString(2, name);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            logger.info(String.format("Creating new player (name - %s, id - %s)", name, id));
            return UUID.fromString(resultSet.getString("id"));
        });
    }

    @Override
    public Player read(UUID uuid) {
        final Player player = new Player("");
        final Player result =  execute(SQLPlayer.SELECT, statement -> {
            statement.setObject(1, uuid);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                player.setId((UUID) resultSet.getObject("id"));
                player.setName(resultSet.getString("name"));
            }
            return player;
        });
        logger.info(String.format("Reading details of the player (name - %s, id - %s)", result.getName(), result.getId()));
        return result;
    }

    @Override
    public UUID update(Player player) {
        final UUID id = player.getId();
        final String name = player.getName();
        return execute(SQLPlayer.UPDATE, statement -> {
            statement.setObject(2, id);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            logger.info(String.format("Updating details of the player (name - %s, id - %s)", name, id));
            return (UUID) resultSet.getObject("id");
        });
    }

    @Override
    public boolean delete(Player player) {
        return execute(SQLPlayer.DELETE, statement -> {
            statement.setObject(1, player.getId());
            logger.warn(String.format("Deleting players with name - %s, id - %s", player.getName(), player.getId()));
            return statement.executeQuery().next();
        });
    }

    private <R> R execute(SQLPlayer sql,
                          SQLFunction<PreparedStatement, R> statementRSQLFunction) {
        R result = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql.QUERY)) {
            result = statementRSQLFunction.apply(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Connection getConnection() {
        return dataConnection.get();
    }

    private enum SQLPlayer {
        INSERT("INSERT INTO players(id, name) VALUES (?,?) RETURNING id"),
        SELECT("SELECT * FROM players WHERE id = ?"),
        UPDATE("UPDATE players SET name = ? WHERE id = ? RETURNING id"),
        DELETE("DELETE FROM players WHERE id = ? RETURNING id");

        String QUERY;

        SQLPlayer(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}

