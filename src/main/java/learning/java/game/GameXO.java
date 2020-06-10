package learning.java.game;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Random;

public class GameXO {
    private final String id;
    private final String type;
    private final String name;
    private final Player playerX;
    private final Player playerO;
    private final String[][] field = new String[3][3];
    private String turn;
    @JsonIgnore
    private String nextTurn;
    private Player winner;

    public GameXO(String id, String type, String name, Player playerX, Player playerO) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.playerX = playerX;
        this.playerO = playerO;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Player getPlayerX() {
        return playerX;
    }

    public Player getPlayerO() {
        return playerO;
    }

    public String[][] getField() {
        return field;
    }

    public String getTurn() {
        return turn;
    }

    public void setField(int x, int y) {
        field[x][y] = turn;
        nextTurn(field);
        String isWin = WinnerController.checkLineWinner(field);
        if ( isWin != null) {
            if (isWin.equals("X"))
                winner = playerX;
            if (isWin.equals("O"))
                winner = playerO;
        }
    }

    public void setTurn(String turn) {
        this.turn = turn;
        if (turn.equals("X") && turn.equals("O"))
            throw new RuntimeException();
        if (turn.equals("X"))
            nextTurn = "O";
        if (turn.equals("O"))
            nextTurn = "X";
        }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
    public static GameXO gameDefault() {
        return new GameXO("e379002d-6fba-4899-96b2-38371f458418",
                "singleplayer",
                "10235",
                new Player("player", "2a5ba390-c7b3-40bf-903c-8fba36913092"),
                new Player("AI", null));
    }
    public void nextTurn (String[][] field) {
        Random randomInt = new Random();
        int check = -1;
        while (check == -1) {
            int x = randomInt.nextInt(3);
            int y = randomInt.nextInt(3);
            if (field[x][y] == null) {
                field[x][y] = nextTurn;
                check = 1;
            }
        }

    }
}
