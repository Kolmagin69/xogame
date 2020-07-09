package learning.java.game.controller;

import learning.java.game.dao.Dao;
import learning.java.game.exception.NotFoundExceptions;
import learning.java.game.model.CreateGame;
import learning.java.game.model.Figure;
import learning.java.game.model.Game;
import learning.java.game.model.Point;
import learning.java.game.rest.request.CreateGameRequest;
import learning.java.game.rest.request.TurnGameRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameServiceSingle implements GameService {

    @Autowired
    private GameController gameControl;

    @Autowired
    private CreateGame gameXO;

    @Autowired
    private Dao<Game, UUID> dao;

    @Override
    public Game getGameFromId(String id) {
        UUID key = UUID.fromString(id);
        Game game = dao.read(key);

        if(game == null)
            throw new NotFoundExceptions("Not found game with this id: " + key);

        return dao.read(key);
    }

    @Override
    public Game postNewGame(CreateGameRequest createGameRequest) {
        Figure postFigure = createGameRequest.getSide();

        if (postFigure == null)
            throw new IllegalArgumentException("Sent request with incorrect body. " +
                    "Try - {\"side\":\"X\"}");

        Game game = gameXO.newGame(postFigure);
        game.setTurn(gameControl.currentFigure(game.getField()));
        dao.create(game);
        return game;
    }

    @Override
    public Game turnGameFromId(TurnGameRequest turnGameRequest, String id) {
        if (turnGameRequest == null ) {
            throw new IllegalArgumentException("Sent request with incorrect body." +
                    " Try - {\"position\":\"[2,2]\"}");
        }
        if (id == null)
            throw new IllegalArgumentException("Sent request to incorrect address." +
                    " Try - game/{UUID}/turn");
        Game gameXO = dao.read(UUID.fromString(id));
        if (gameXO == null)
            throw new NotFoundExceptions("Game with id: \"" + id + "\" Not found");

        Point point = new Point(turnGameRequest.getX(), turnGameRequest.getY());
        gameControl.letsPlay(gameXO, point);

        return gameXO;
    }
}
