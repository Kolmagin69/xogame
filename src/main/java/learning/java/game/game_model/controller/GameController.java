package learning.java.game.game_model.controller;

import learning.java.game.game_model.model.Field;
import learning.java.game.game_model.model.Figure;
import learning.java.game.game_model.model.Game;
import learning.java.game.game_model.model.Point;

public interface GameController {

    void letsPlay(Game game, Point point);

    Figure currentFigure(final Field field);

}
