package learning.java.game.gameModel.controller;

import learning.java.game.gameModel.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class GameControllerSingle implements GameController {
    @Autowired
    private WinnerController win;

    @Autowired
    private TurnController turn;

    @Autowired
    private MoveController move;

    public void letsPlay(Game game, Point point) {
        Field field = game.getField();

        if (game.getPlayers().get(0).getFigure() == Figure.X) {
            move.applyFigure(field, point, turn.currentFigure(field));
            randomMove(field);
            game.setWinner(win.checkLineWinner(field));
            return;
        }
        randomMove(field);
        move.applyFigure(field, point, turn.currentFigure(field));
        game.setWinner(win.checkLineWinner(field));
    }

    private void randomMove(Field field) {
        Random random = new Random();
        Figure figure = turn.currentFigure(field);
        int size = field.getSize();
        while (!(move.applyFigure (field, new Point(random.nextInt(size), random.nextInt(3)), figure)));


    }






}
