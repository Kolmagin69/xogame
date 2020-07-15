package learning.java.game.dao;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DataConnection {

    private final String user = "xogame";

    private final String password = "123321";

    private final String url = "jdbc:postgresql://localhost:5432/xogame";

    private final Connection connection = DriverManager.getConnection(url, user, password);

    public DataConnection() throws SQLException {
    }

    public Connection get() {
        return connection;
    }
}
