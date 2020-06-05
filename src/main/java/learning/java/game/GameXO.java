package learning.java.game;

public class GameXO {
    private final String id;
    private final String type;
    private final String name;
    private final Player playerX;
    private final Player playerO;
    private final String[][] field = new String[3][3];
    private String turn;
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
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}
