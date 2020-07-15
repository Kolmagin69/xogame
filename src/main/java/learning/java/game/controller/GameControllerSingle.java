package learning.java.game.controller;

import learning.java.game.model.Field;
import learning.java.game.model.Figure;
import learning.java.game.model.Game;
import learning.java.game.model.Point;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class GameControllerSingle implements GameController {

    public void letsPlay(Game game, Point point) {
        Field field = game.getField();

        if (game.getPlayers().get(0).getFigure() == Figure.X) {
            applyFigure(field, point);
            randomMove(field);
            game.setWinner(checkLineWinner(field));
            return;
        }
        randomMove(field);
        applyFigure(field, point);
        game.setWinner(checkLineWinner(field));
    }

    private void randomMove(final Field field) {
        Random random = new Random();
        Figure figure = currentFigure(field);
        int size = field.getSize();
        Point point = new Point(random.nextInt(size), random.nextInt(size));

        try {
            field.setFigure(point, figure);
        } catch (IllegalArgumentException e) {
            randomMove(field);
        }
    }

    private void applyFigure(final Field field, final Point point) {
        field.setFigure(point, currentFigure(field));
    }

    public Figure currentFigure(final Field field) {
        if (field.getCounterFigure() % 2 == 1) return Figure.O;
        return Figure.X;
    }

    private final Figure X = Figure.X;
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
