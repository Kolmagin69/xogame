package learning.java.game.gameModel.controller;

import learning.java.game.gameModel.model.Field;
import learning.java.game.gameModel.model.Figure;
import learning.java.game.gameModel.model.Point;
import org.springframework.stereotype.Component;

@Component
public class WinnerController {
    private  final Figure X = Figure.X;
    private final Figure O = Figure.O;

    public Figure checkLineWinner(final Field field) {

        int fieldSize = field.getSize();

        int counterX = 0;

        int counterO = 0;

        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                Point point = new Point(i, j);
                if ((X).equals(field.getFigure(point)))
                    counterX++;
                if ((X).equals(field.getFigure(point)))
                    counterO++;
                if (counterX == fieldSize)
                    return X;
                if (counterO == fieldSize)
                    return O;
            }
            counterO = 0;
            counterX = 0;
        }

        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                Point point = new Point(j, i);
                if ((X).equals(field.getFigure(point)))
                    counterX++;
                if ((O).equals(field.getFigure(point)))
                    counterO++;
                if (counterX == fieldSize)
                    return X;
                if (counterO == fieldSize)
                    return O;
            }
            counterO = 0;
            counterX = 0;
        }

        for (int i = 0; i < fieldSize; i++) {
            Point point = new Point(i, i);
            if ((X).equals(field.getFigure(point)))
                counterX++;
            if ((O).equals(field.getFigure(point)))
                counterO++;
            if (counterX == fieldSize)
                return X;
            if (counterO == fieldSize)
                return O;
        }

        counterO = 0;
        counterX = 0;
        int pointX = fieldSize;
        for (int i = 0; i < fieldSize; i++) {
            Point point = new Point(--pointX, i);
            if ((X).equals(field.getFigure(point)))
                counterX++;
            if ((O).equals((field.getFigure(point))))
                counterO++;
            if (counterX == fieldSize)
                return X;
            if (counterO == fieldSize)
                return O;
        }

        return null;
    }

}

