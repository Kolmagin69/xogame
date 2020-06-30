package learning.java.game.game_model.controller;

import learning.java.game.dao.Dao;
import learning.java.game.game_model.model.CreateGame;
import learning.java.game.game_model.model.Figure;
import learning.java.game.game_model.model.Game;
import learning.java.game.game_model.model.Point;
import learning.java.game.rest_controller.request_body.PostBody;
import learning.java.game.rest_controller.request_body.TurnBody;
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
    private Dao dao;

    @Override
    public Game getGameFromId(String id) {
        return (Game) dao.read(UUID.fromString(id));
    }

    @Override
    public Game postNewGame(PostBody postBody) {
        Figure postFigure = postBody.getSide();

        if (postFigure == null)
            throw new IllegalArgumentException();

        Game game = gameXO.newGame(postFigure);
        game.setTurn(gameControl.currentFigure(game.getField()));
        dao.create(game);
        return game;
    }

    @Override
    public Game turnGameFromId(TurnBody turnBody, String id) {
        if (turnBody == null || id == null)
            throw new IllegalArgumentException();

        Game gameXO = (Game) dao.read(UUID.fromString(id));
        if (gameXO == null)
            throw new IllegalArgumentException();

        Point point = new Point(turnBody.getX(), turnBody.getY());
        gameControl.letsPlay(gameXO, point);

        return gameXO;
    }
}
