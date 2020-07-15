package learning.java.game.dao;

import com.sun.istack.internal.NotNull;
import learning.java.game.model.Figure;
import learning.java.game.model.Game;
import learning.java.game.model.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GamesDao implements Dao<Game, UUID> {

    private final Connection connection;

    private final FieldsDao fieldsDao;

    private final PlayersDao playersDao;

    public GamesDao(@NotNull Connection connection,
                    @NotNull FieldsDao fieldsDao,
                    @NotNull PlayersDao playersDao){
        this.connection = connection;
        this.fieldsDao = fieldsDao;
        this.playersDao = playersDao;
    }

    @Override
    public UUID create(Game game) throws SQLException {
        UUID id = UUID.randomUUID();
        game.setId(id);
        int fieldId = fieldsDao.create(game.getField());
        try(PreparedStatement statement = connection.prepareStatement(SQLGames.INSERT.QUERY)) {
            statement.setObject(1, id);
            statement.setString(2, game.getType());
            statement.setString(3, game.getName());
            statement.setString(4, game.getTurn().toString());
            if (game.getWinner() == null) {
                statement.setString(5, null);
            } else {
                statement.setString(5, game.getWinner().toString());
            }
            statement.setInt(6,fieldId);
            statement.executeQuery();
        } finally {
            connection.close();
        }
        createPlayerInDao(game);
        return id;
    }

    @Override
    public Game read(UUID uuid) throws SQLException {
        Game game = new Game();
        try (PreparedStatement statement = connection.prepareStatement(SQLGames.SELECT.QUERY)) {
            statement.setObject(1, uuid);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                game.setId((UUID) resultSet.getObject("id"));
                game.setName(resultSet.getString("name"));
                game.setType(resultSet.getString("type"));

                Figure figureTurn = Figure.figureFromString(resultSet.getString("turn"));
                game.setTurn(figureTurn);

                Figure figureWinner = Figure.figureFromString(resultSet.getString("winner"));
                game.setWinner(figureWinner);

                int fieldsId = resultSet.getInt("field_id");
                game.setField(fieldsDao.read(fieldsId));

                List<Player> players = readPlayerFromDao(game);
                game.setPlayers(players);
            }
        } finally {
            connection.close();
        }
        return game;
    }

    @Override
    public UUID update(@ NotNull Game game) throws SQLException {
        UUID id = game.getId();
        try (PreparedStatement statement = connection.prepareStatement(SQLGames.UPDATE.QUERY)) {
            statement.setString(1, game.getTurn().toString());

            Figure winnerF = game.getWinner();
            String winnerS = (winnerF == null ? null : winnerF.toString());
            statement.setString(2, winnerS);

            statement.setObject(3, game.getId());
            statement.executeQuery();
        } finally {
            connection.close();
        }
        fieldsDao.update(game.getField());
        return id;
    }

    @Override
    public boolean delete(Game game) throws SQLException {
        UUID id = game.getId();
        boolean result;

        List<Player> players = readPlayerFromDao(game);
        for (Player player: players) {
            playersDao.delete(player);
        }

        try (PreparedStatement statement = connection.prepareStatement(SQLGames.DELETE.QUERY)) {
            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();
            result = resultSet.next();
        } finally {
            connection.close();
        }
        return result;
    }

    private enum SQLGames {
        INSERT("INSERT INTO games (id, type, name, turn, winner, field_id) VALUES (?,?,?,?,?,?) RETURNING id"),
        SELECT("SELECT * FROM games WHERE id = (?)"),
        UPDATE("UPDATE games SET turn = (?), winner = (?) WHERE id = (?) RETURNING id"),
        DELETE("DELETE FROM games WHERE id = ? RETURNING id"),
        SELECT_PLAYERS("SELECT p.id, p.name, p.figure FROM players AS p JOIN games AS g ON p.game_id = g.id WHERE g.id = (?)");

        String QUERY;

        SQLGames(String QUERY) {
            this.QUERY = QUERY;
        }
    }

    private void createPlayerInDao(Game game) throws SQLException {
        UUID gameId = game.getId();
        List<Player> players = game.getPlayers();
        for (Player player : players) {
            player.setGameId(gameId);
            playersDao.create(player);
        }
    }

    private List<Player> readPlayerFromDao(Game game) throws SQLException {
        UUID gameId = game.getId();
        List<Player> players = new ArrayList<>();
        Connection connection = new DataConnection().get();
        try (PreparedStatement statement = connection.prepareStatement(SQLGames.SELECT_PLAYERS.QUERY)) {
            statement.setObject(1, gameId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Player player = new Player() {{
                    setGameId(gameId);
                    setFigure(Figure.figureFromString(rs.getString("figure")));
                    setId((UUID) rs.getObject("id"));
                    setName(rs.getString("name"));
                }};
                players.add(player);
            }
        } finally {
            connection.close();
        }
        return players;
    }

    public void close() throws SQLException {
        connection.close();
        fieldsDao.close();
        playersDao.close();
    }
}
