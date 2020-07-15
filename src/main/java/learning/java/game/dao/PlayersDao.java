package learning.java.game.dao;

import com.sun.istack.internal.NotNull;
import learning.java.game.model.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlayersDao implements Dao<Player, UUID> {

    private final Connection connection;

    public PlayersDao(@NotNull Connection connection){
        this.connection = connection;
    }

    @Override
    public UUID create(Player player) throws SQLException {
        UUID id;
        try (PreparedStatement statement = connection.prepareStatement(SQLPlayer.INSERT.QUERY)) {
            statement.setObject(1, player.getId());
            statement.setString(2, player.getName());
            statement.setString(3, player.getFigure().toString());
            statement.setObject(4, player.getGameId());

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            id = UUID.fromString(resultSet.getString("id"));
        }
        return id;
    }

    @Override
    public Player read(UUID uuid){
        throw new UnsupportedOperationException();
    }

    @Override
    public UUID update(Player player){
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Player player) throws SQLException {
        UUID id = player.getId();
        boolean result;
        try (PreparedStatement statement = connection.prepareStatement(SQLPlayer.DELETE.QUERY)){
            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();
            result = resultSet.next();
        } finally {
            connection.close();
        }
        return result;
    }

    private enum SQLPlayer {
        INSERT("INSERT INTO players(id, name, figure, game_id) VALUES (?,?,?,?) RETURNING id"),
        DELETE("DELETE FROM players WHERE id = ? RETURNING id");

        String QUERY;

        SQLPlayer(String QUERY) {
            this.QUERY = QUERY;
        }

    }

    public void close() throws SQLException {
        connection.close();
    }
}
