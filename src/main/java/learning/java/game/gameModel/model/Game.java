package learning.java.game.gameModel.model;

import com.google.gson.Gson;

import java.util.List;
import java.util.UUID;

public class Game {

    private final UUID id = UUID.randomUUID();

    private final String type;

    private final String name;

    private final List<Player> players ;

    private final Field field;

    private Figure turn = Figure.X;

    private Figure winner = null;

    public Game(String type, String name, List players, Field field) {
        this.field = field;
        this.type = type;
        this.name = name;
        this.players = players;

    }

    public UUID getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }


    public Field getField() {
        return field;
    }

    public Figure getTurn() {
        return turn;
    }

    public void setTurn(Figure turn) {
        this.turn = turn;
    }

    public Figure getWinner() {
        return winner;
    }

    public void setWinner(Figure winner) {
        this.winner = winner;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
