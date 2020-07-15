package learning.java.game.dao;

import com.sun.istack.internal.NotNull;
import learning.java.game.model.Field;
import learning.java.game.model.Figure;

import java.sql.*;

public class FieldsDao implements Dao<Field, Integer> {

    @NotNull
    private final Connection connection;

    public FieldsDao(final Connection connection){
        this.connection = connection;
    }

    @Override
    public Integer create(Field field) throws SQLException {
        int id;
        try (PreparedStatement statement = connection.prepareStatement(SQLField.INSERT.QUERY)) {
            Array array = connection.createArrayOf("varchar", field.getFigures());
            statement.setInt(1, field.getSize());
            statement.setArray(2, array);
            ResultSet result = statement.executeQuery();
            result.next();
            id = result.getInt("id");
        } finally {
            connection.close();
        }
        return id;
    }

    @Override
    public Field read(Integer key) throws SQLException {
        Field resultField = new Field();
        try(PreparedStatement statement = connection.prepareStatement(SQLField.SELECT.QUERY)) {
            statement.setInt(1, key);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            resultField.setId(resultSet.getInt("id"));
            resultField.setSize(resultSet.getInt("size"));

            Array a = resultSet.getArray("figures");
            resultField.setFigures((arrayFiguresFromString((String[][]) a.getArray())));
        } finally {
            connection.close();
        }
        return resultField;
    }


    @Override
    public Integer update(Field field) throws SQLException {
        int id = field.getId();
        try (PreparedStatement statement = connection.prepareStatement(SQLField.UPDATE.QUERY)) {
            Array array = connection.createArrayOf("varchar", field.getFigures());
            statement.setArray(1,array);
//            statement.setInt(2, id);
            statement.executeQuery();
        } finally {
            connection.close();
        }
        return id;
    }

    @Override
    public boolean delete(Field field) throws SQLException {
        boolean result;
        try(PreparedStatement statement = connection.prepareStatement(SQLField.DELETE.QUERY)) {
            statement.setInt(1,field.getId());
            result = statement.executeQuery().next();
        } finally {
            connection.close();
        }
        return result;
    }

    public int count() throws SQLException {
        int count;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLField.COUNT.QUERY)){
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            count = resultSet.getInt("count");
        } finally {
            connection.close();
        }
        return count;
    }

    private enum SQLField {

        INSERT("INSERT INTO fields (size, figures) VALUES ((?), (?)) RETURNING id"),
        SELECT("SELECT * FROM fields WHERE id = (?)"),
        UPDATE("UPDATE fields SET figures = (?)  RETURNING id"),
        DELETE("DELETE FROM fields WHERE id = (?) RETURNING id"),
        COUNT("SELECT COUNT(id) FROM fields");

        private String QUERY;

        SQLField(String QUERY) {
            this.QUERY = QUERY;
        }
    }

    private Figure[][] arrayFiguresFromString (String[][] strArray) {
        if(!(isArrayRectangular(strArray)))
            throw new IllegalArgumentException("The array not is rectangular");
        int length = strArray.length;
        int width = strArray[0].length;
        Figure[][] resultFigure = new Figure[length][width];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                resultFigure[i][j] = Figure.figureFromString(strArray[i][j]);
            }
        }
        return resultFigure;
    }

    private boolean isArrayRectangular(Object[][] objects) {
        int size = objects[0].length;
        for (int i = 1; i < objects.length; i++)
            if (objects[i].length != size)
                return false;
        return true;
    }

    public void close() throws SQLException {
        connection.close();
    }
}
