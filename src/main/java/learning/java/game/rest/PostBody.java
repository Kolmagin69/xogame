package learning.java.game.rest;

import learning.java.game.model.Figure;

public class PostBody {
    private Figure side;

    public Figure getSide() {
        return side;
    }

    public void setSide(Figure side) {
        this.side = side;
    }
}
