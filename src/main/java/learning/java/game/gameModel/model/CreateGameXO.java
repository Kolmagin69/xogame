package learning.java.game.gameModel.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateGameXO implements CreateGame {

    private String type = "singlePlayer";

    private String name = "XO";

    public Game newGame(Figure figure) {
        return new Game(type, name, createPlayer(figure), new Field(3));
    }

    private List<Player> createPlayer (Figure figure) {
        List players = new ArrayList();
        if (figure == Figure.X) {
            players.add(new PlayerSingle("player", figure));
            players.add(new PlayerSingle("AI", Figure.O));
            return players;
        }

        players.add(new PlayerSingle("player", figure));
        players.add(new PlayerSingle("AI", Figure.X));
        return players;

    }

}
