package learning.java.game.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class DataConnection  {

    public static final Logger logger = Logger.getLogger(DataConnection.class.getName());


    private String user;

    private String password;

    private String url;

    @Autowired
    public DataConnection(@Value("${user}") String user,
                           @Value("${password}") String password,
                           @Value("${url}") String url){
        this.user = user;
        this.password = password;
        this.url = url;
    }

    public Connection get() {
        Connection connection = null;
        try {
            connection =  DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            logger.log(Level.WARNING,"problem with connection", SQLException.class);
            e.printStackTrace();
        }
        logger.info("connect to DB. Client " + user);
        return connection;
    }
}
