package learning.java.game.model;

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

    private List<Player> createPlayer(Figure figure) {
         List<Player> players = new ArrayList<>();
        if (figure == Figure.X) {
            players.add(new Player("player", figure));
            players.add(new Player("AI", Figure.O));
            return players;
        }

        players.add(new Player("player", figure));
        players.add(new Player("AI", Figure.X));
        return players;
    }
}
