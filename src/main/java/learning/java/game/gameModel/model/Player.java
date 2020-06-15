package learning.java.game.gameModel.model;

import java.util.UUID;

public interface Player {
    public String getName();

    public Figure getFigure();

    public UUID getId ();
}
