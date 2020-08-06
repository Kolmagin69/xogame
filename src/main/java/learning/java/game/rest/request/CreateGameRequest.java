package learning.java.game.rest.request;

import learning.java.game.model.Figure;
import learning.java.game.model.Player;

import java.util.UUID;

public class CreateGameRequest {

    private Figure side;

    private Player player;

    public Figure getSide() {
        return side;
    }

    public void setSide(Figure side) {
        this.side = side;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
