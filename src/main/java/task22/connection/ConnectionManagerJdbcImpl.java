package task22.connection;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ConnectionManagerJdbcImpl
 * класс реализует интерфейс ConnectionManager
 * для получения соединения к БД
 */
public class ConnectionManagerJdbcImpl implements ConnectionManager {

    public static ConnectionManager INSTANCE;
    private static final Logger logger = LogManager.getLogger(ConnectionManagerJdbcImpl.class.getName());


    private ConnectionManagerJdbcImpl() {
    }

    /**
     * Возвращает единственный экземпляр ConnectionManager
     * @return ConnectionManager
     */
    public static ConnectionManager getInstance() {
        if (INSTANCE == null){
            INSTANCE = new ConnectionManagerJdbcImpl();

        }
        return INSTANCE;
    }

    /**
     * Метод получает соединение с сервером MySql
     * и инициализирует работу с БД my_blog
     * @return
     */
    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/my_blog",
                    "root",
                    "npatfb4psdf");
            logger.trace ("connection established");
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
        return connection;
    }
}
