package learning.java.game.rest;

import learning.java.game.controller.GameService;
import learning.java.game.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

