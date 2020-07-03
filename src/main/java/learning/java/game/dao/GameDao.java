package learning.java.game.dao;

import learning.java.game.model.Game;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class GameDao implements Dao<Game, UUID> {

    private Map<UUID, Game> dataBase = new HashMap<>();

    @Override
    public boolean create(Game game) {
        UUID id = game.getId();

        return dataBase.putIfAbsent(id, game) == null ? true : false;
    }

    @Override
    public Game read(UUID id) {
        return dataBase.get(id);
    }

    @Override
    public boolean update(Game game) {
        UUID id = game.getId();

        return dataBase.replace(id, game) == null ? true : false;
    }

    @Override
    public boolean delete(Game game) {

        throw new UnsupportedOperationException();

    }
}
