package learning.java.game.gameModel.model;

import java.util.UUID;

public class PlayerSingle implements Player {
    
    private final UUID id = UUID.randomUUID();

    private final String name;

    private final Figure figure;

    public PlayerSingle(String name, Figure figure) {
        this.name = name;
        this.figure = figure;
    }

    public String getName() {
        return name;
    }

    public Figure getFigure() {
        return figure;
    }

    public UUID getId (){
        return id;
    }
}
