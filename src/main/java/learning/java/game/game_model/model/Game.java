package learning.java.game.game_model.model;

import java.util.List;
import java.util.UUID;

public class Game {

    private UUID id = UUID.randomUUID();

    private String type;

    private String name;

    private List<Player> players;

    private Field field;

    private Figure turn = Figure.X;

    private Figure winner = null;

    public Game() {

    };

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

    public void setId(UUID id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public static void main(String[] args) {
        System.out.println("game" + UUID.randomUUID());
    }
}
