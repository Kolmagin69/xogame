package learning.java.game.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class Player {

    private UUID id = UUID.randomUUID();

    private String name;

    private Figure figure;

    @JsonIgnore
    private UUID gameId;

    public Player() {
    }

    public Player(String name, Figure figure) {
        this.name = name;
        this.figure = figure;
    }

    public String getName() {
        return name;
    }

    public Figure getFigure() {
        return figure;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }
}
