package learning.java.game.rest;

import learning.java.game.dao.PlayersDao;
import learning.java.game.model.Player;
import learning.java.game.rest.request.PlayerName;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("player")
public class PlayerRestController {

    PlayersDao dao = new PlayersDao();

    @PostMapping()
    public Player postPlayer(final @RequestBody PlayerName name) {
        Player player = new Player(name.getName());
        dao.create(player);
        return player;
    }

    @GetMapping("{id}")
    public Player gatPlayer(final @PathVariable String id) {
        return dao.read(UUID.fromString(id));
    }
}
