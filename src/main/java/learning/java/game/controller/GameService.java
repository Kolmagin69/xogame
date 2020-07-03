package learning.java.game.controller;

import learning.java.game.model.Game;
import learning.java.game.rest.PostBody;
import learning.java.game.rest.TurnBody;

public interface GameService {

    Game postNewGame(PostBody postBody);

    Game getGameFromId(String id);

    Game turnGameFromId(TurnBody turnBody, String id);
}
