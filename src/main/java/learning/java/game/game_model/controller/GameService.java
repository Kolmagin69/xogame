package learning.java.game.game_model.controller;

import learning.java.game.game_model.model.Game;
import learning.java.game.rest_controller.request_body.PostBody;
import learning.java.game.rest_controller.request_body.TurnBody;

public interface GameService {

    Game postNewGame(PostBody postBody);

    Game getGameFromId(String id);

    Game turnGameFromId(TurnBody turnBody, String id);
}
