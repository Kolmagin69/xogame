package learning.java.game.rest_controller.request_body;

import learning.java.game.dao.GameDao;
import learning.java.game.game_model.controller.GameController;
import learning.java.game.game_model.controller.GameService;
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
    GameService gameService;

    @GetMapping("{id}")
    @ResponseBody
    public Game gameFromId(@PathVariable String id) {
        return gameService.getGameFromId(id);
    }

    @PostMapping
    public @ResponseBody Game postGame(@RequestBody PostBody postBody) {
        return gameService.postNewGame(postBody);
    }

    @PostMapping("{id}/turn")
    public  @ResponseBody Game turn(@RequestBody TurnBody turnBody,
                                     @PathVariable String id) {

        return gameService.turnGameFromId(turnBody, id);
    }


}

