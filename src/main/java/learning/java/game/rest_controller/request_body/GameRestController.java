package learning.java.game.rest_controller.request_body;

import learning.java.game.dao.GameDao;
import learning.java.game.game_model.controller.GameController;
import learning.java.game.game_model.model.CreateGame;
import learning.java.game.game_model.model.Figure;
import learning.java.game.game_model.model.Game;
import learning.java.game.game_model.model.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("game")
public class GameRestController {
    @Autowired
    private GameController gameControl;
    @Autowired
    private CreateGame gameXO;
    @Autowired
    private GameDao dao;

    @GetMapping("{id}")
    @ResponseBody
    public Game gameFromId(@PathVariable String id) {
        return dao.getDataBase().get(UUID.fromString(id));
    }

    @PostMapping
    public @ResponseBody Game postGame(@RequestBody PostBody postBody) {
        Figure postFigure = postBody.getSide();

        if (postFigure == null)
            throw new IllegalArgumentException();

        Game game = gameXO.newGame(postFigure);
        game.setTurn(gameControl.currentFigure(game.getField()));
        dao.setDataBase(game.getId(), game);
        return game;

    }

    @PostMapping("{id}/turn")
    public  @ResponseBody Game turn(@RequestBody TurnBody turnBody,
                                     @PathVariable String id) {
        if (turnBody == null || id == null)
            throw new IllegalArgumentException();

        Game gameXO = dao.getDataBase().get(UUID.fromString(id));
        if (gameXO == null)
            throw new IllegalArgumentException();

        Point point = new Point(turnBody.getX(), turnBody.getY());
        gameControl.letsPlay(gameXO, point);
        return gameXO;
    }


}

