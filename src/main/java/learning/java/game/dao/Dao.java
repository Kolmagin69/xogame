package learning.java.game.dao;

public interface Dao<T> {

    boolean create(Object obj);

    T read(Object obj);

    boolean update(Object obj);

    boolean delete(Object obj);


}
