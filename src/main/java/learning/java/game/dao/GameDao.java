package learning.java.game.dao;

import learning.java.game.game_model.model.Game;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class GameDao {
    private Map<UUID, Game> dataBase = new HashMap<>();

    public Map<UUID, Game> getDataBase() {
        return dataBase;
    }

    public void setDataBase(UUID id, Game game) {
        dataBase.put(id, game);
    }
}
