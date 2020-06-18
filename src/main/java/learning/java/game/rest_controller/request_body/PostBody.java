package learning.java.game.rest_controller.request_body;

import learning.java.game.game_model.model.Figure;

public class PostBody {
    private Figure side;

    public Figure getSide() {
        return side;
    }

    public void setSide(Figure side) {
        this.side = side;
    }
}
