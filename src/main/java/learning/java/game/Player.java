package learning.java.game;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Player {
    private final String type;

    private final String id;

    public Player(String type, String id) {
        this.type = type;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }
}
