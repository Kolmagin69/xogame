package learning.java.game.springController;

import learning.java.game.DAO.DAO;
import learning.java.game.gameModel.controller.GameController;
import learning.java.game.gameModel.controller.GameControllerSingle;
import learning.java.game.gameModel.controller.TurnController;
import learning.java.game.gameModel.model.CreateGame;
import learning.java.game.gameModel.model.Figure;
import learning.java.game.gameModel.model.Game;
import learning.java.game.gameModel.model.Point;
import learning.java.game.requestBody.PostBody;
import learning.java.game.requestBody.TurnBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("gameXO")
public class SpringGameController {
    @Autowired
    private CreateGame gameXO;

    @Autowired
    private DAO dao;

    @Autowired
    GameController gameControl;

    @Autowired
    TurnController turnController;


    @GetMapping("{id}")
    @ResponseBody
    public Game gameFromId(@PathVariable String id) {
        return dao.getDataBase().get(UUID.fromString(id));
    }

    @PostMapping
    @ResponseBody
    public Game postGame (@RequestBody PostBody postBody) {
        Game game = gameXO.newGame(postBody.getSide());
        game.setTurn(turnController.currentFigure(game.getField()));
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

