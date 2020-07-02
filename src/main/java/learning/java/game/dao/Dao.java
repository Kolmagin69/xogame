package learning.java.game.dao;

public interface Dao<Entity, Key> {

    boolean create(Entity entity);

    Entity read(Key key);

    boolean update(Entity entity);

    boolean delete(Entity entity);


}
