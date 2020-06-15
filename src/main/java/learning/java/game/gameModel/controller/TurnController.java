package learning.java.game.gameModel.controller;

import learning.java.game.gameModel.model.Field;
import learning.java.game.gameModel.model.Figure;
import org.springframework.stereotype.Component;

import java.util.EnumSet;

@Component
public class  TurnController {


    public Figure currentFigure(final Field field) {
        if(field.getCounterFigure() % 2 == 1) return Figure.O;
        return Figure.X;
    }

}
