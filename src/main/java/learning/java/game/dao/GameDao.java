package learning.java.game.dao;

import learning.java.game.game_model.model.Game;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class GameDao implements Dao<Game> {

    private Map<UUID, Game> dataBase = new HashMap<>();

    @Override
    public boolean create(Object obj) {
        if (!(obj instanceof Game))
            throw new IllegalArgumentException();

        Game game = (Game) obj;
        UUID id = game.getId();

        return dataBase.putIfAbsent(id, game) == null ? true : false;
    }

    @Override
    public Game read(Object obj) {
        if (!(obj instanceof UUID))
            throw new IllegalArgumentException();

        return dataBase.get((UUID) obj);
    }

    @Override
    public boolean update(Object obj) {
        if (!(obj instanceof Game))
            throw new IllegalArgumentException();

        Game game = (Game) obj;
        UUID id = game.getId();

        return dataBase.replace(id, game) == null ? true : false;
    }

    @Override
    public boolean delete(Object obj) {

        throw new UnsupportedOperationException();

    }
}
