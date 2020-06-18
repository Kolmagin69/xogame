package learning.java.game.rest_controller;

import learning.java.game.dao.GameDao;
import learning.java.game.game_model.controller.GameController;
import learning.java.game.game_model.model.CreateGame;
import learning.java.game.game_model.model.Game;
import learning.java.game.game_model.model.Point;
import learning.java.game.rest_controller.request_body.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("gameXO")
public class GameRestController {
    @Autowired
    GameController gameControl;
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
    @ResponseBody
    public Game postGame(@RequestBody PostBody postBody) {
        Game game = gameXO.newGame(postBody.getSide());
        game.setTurn(gameControl.currentFigure(game.getField()));
        dao.setDataBase(game.getId(), game);
        return game;

    }

    @PostMapping("{id}/turn")
    @ResponseBody
    public Game turn(@RequestBody TurnBody turnBody,
                     @PathVariable String id) {
        Game gameXO = dao.getDataBase().get(UUID.fromString(id));
        Point point = new Point(turnBody.getX(), turnBody.getY());
        gameControl.letsPlay(gameXO, point);
        return gameXO;
    }


}

