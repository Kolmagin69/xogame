package learning.java.game;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("game")
public class GameController {
    private Map<String, GameXO> dataBase= new HashMap<>();
    private GameXO gameDefault = GameXO.gameDefault();


    @GetMapping("{id}")
    public GameXO gameFromId(@PathVariable String id) {

        return dataBase.get(id);
    }
    @PostMapping
    @ResponseBody
    public GameXO postGame (@RequestBody PostBody postBody) {
        gameDefault.setTurn(postBody.getSide());
        dataBase.put(gameDefault.getId(), gameDefault);
        return gameDefault;
    }
    @PostMapping("{id}/turn")
    @ResponseBody
    public GameXO turn(@RequestBody TurnBody turnBody,
                       @PathVariable String id) {
        GameXO gameXO = dataBase.get(id);
        gameXO.setField(turnBody.getX(), turnBody.getY());
        return gameXO;
    }



}

