package learning.java.game.controller;

import learning.java.game.GameXO;
import learning.java.game.Player;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("game")
public class GameController {
    private GameXO gameDefault;
    private Map<String, GameXO> dataBase= new HashMap<>();

    @GetMapping
    //временный гет
    public GameXO game() {
        return gameDefault;
    }
    @GetMapping("{id}")
    //по запросу GET /game/e379002d-6fba-4899-96b2-38371f458418 HTTP/1.0
    //вернет дефолтный game
    public GameXO gameFromId(@PathVariable String id) {

        return dataBase.get(id);
    }
    // добавил заголовок POST, просто чтобы он работал
    // и отправлял json по запросу "POST /game HTTP/1.0" без тела запроса
    @PostMapping
    public GameXO postGame () {
        gameDefault = new GameXO("e379002d-6fba-4899-96b2-38371f458418",
                "singleplayer",
                "10235",
                new Player("player", "2a5ba390-c7b3-40bf-903c-8fba36913092"),
                new Player("AI", null) ) {{
                    setTurn("X");
        }};
        dataBase.put(gameDefault.getId(), gameDefault);
        return gameDefault;
    }



}

