package learning.java.game.rest.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import learning.java.game.exception.IncorrectBodyException;

public class TurnGameRequest {

    private int[] position;

    public TurnGameRequest() {
    }

    public TurnGameRequest(int i){
        position = new int[i];
    }
    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public int getX() {
        if (position == null)
            throw new IncorrectBodyException(message);
        return position[0];
    }

    public int getY() {
        if (position == null)
            throw new IncorrectBodyException(message);
        return position[1];
    }

    public void setX(int i) {
        position[0] = i;
    }

    public void setY(int i) {
        position[0] = i;
    }

    @JsonIgnore
    private String message = "Sent request with incorrect body. Try - {\"position\":\"[2,2]\"}";
}
