package learning.java.game.gameModel.controller;

import learning.java.game.gameModel.model.Field;
import learning.java.game.gameModel.model.Figure;
import learning.java.game.gameModel.model.Point;
import org.springframework.stereotype.Component;

@Component
public class MoveController {

    public boolean applyFigure(final Field field, final Point point, final Figure figure) {
        if(field.getFigure(point) != null)
            return false;
        field.setFigures(point, figure);
        return true;
    }


}
