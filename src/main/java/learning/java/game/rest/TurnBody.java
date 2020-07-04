package learning.java.game.rest;

public class TurnBody {

    private int[] position;

    public TurnBody() {
    }

    public TurnBody(int i){
        position = new int[i];
    }
    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public int getX() {
        return position[0];
    }

    public int getY() {
        return position[1];
    }

    public void setX(int i) {
        position[0] = i;
    }

    public void setY(int i) {
        position[0] = i;
    }

}
