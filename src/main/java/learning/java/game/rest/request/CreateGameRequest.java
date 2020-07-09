package learning.java.game.rest.request;

import learning.java.game.model.Figure;

public class CreateGameRequest {

    private Figure side;

    public Figure getSide() {
        return side;
    }

    public void setSide(Figure side) {
        this.side = side;
    }
}
