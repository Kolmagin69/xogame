package learning.java.game.model;

import java.util.List;
import java.util.UUID;

public class Game {

    private UUID id;

    private String type;

    private String name;

    private Player player1;

    private Player player2;

    private Field field;

    private Figure turn = Figure.X;

    private Figure winner = null;

    public Game() {

    }

    public Game(String type, String name, Player player1, Player player2, Field field) {
        this.field = field;
        this.type = type;
        this.name = name;
        this.player1 = player1;
        this.player1 = player2;
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


    public void setId(UUID id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void setField(Field field) {
        this.field = field;
    }

}
