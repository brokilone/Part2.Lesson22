package task22.dao.user;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import task22.connection.ConnectionManager;
import task22.model.User;


import javax.ejb.EJB;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * UserInfoDaoImpl
 * класс реализует CRUD-операции с объектом User
 * <p>
 * created by Ksenya_Ushakova at 31.05.2020
 */
@EJB
public class UserDaoImpl implements UserDao {
    private ConnectionManager connectionManager;
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class.getName());


    @Inject
    public UserDaoImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;

    }

    /**
     * Добавление пользователя в БД
     *
     * @param user - пользователь
     * @return логин объекта
     * @throws SQLException
     */
    @Override
    public String addUser(User user) throws SQLException {
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO user_info VALUES(?,?,?)");) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getRating());
            preparedStatement.executeUpdate();

            logger.info("Add new user {}", user);

            return user.getLogin();
        }
    }

    /**
     * Поиск пользователя в БД по логину
     *
     * @param login логин пользователя
     * @return опционал объекта
     * @throws SQLException
     */
    @Override
    public Optional<User> getByLogin(String login) throws SQLException {
        User user = null;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM user_info WHERE login = ?");) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                logger.info("Get user by login: {}", login);


                user = new User(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3)
                );
            }
            return Optional.ofNullable(user);
        }
    }

    /**
     * Редактирование данных пользователя в БД
     *
     * @param user - пользователь
     * @throws SQLException
     */
    @Override
    public void updateByLogin(User user) throws SQLException {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE user_info SET password = ?, rating = ? WHERE login = ?")) {
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setInt(2, user.getRating());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.executeUpdate();

            logger.info("Update user: {}", user);

        }
    }

    /**
     * Удаление записи о пользователе в БД по логину
     *
     * @param login
     * @throws SQLException
     */
    @Override
    public void deleteByLogin(String login) throws SQLException {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE FROM user_info WHERE login = ?")) {

            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();

            logger.info("Delete user by login: {}", login);

        }
    }

    @Override
    public List<User> getAllUsers() throws SQLException{
        List<User> result = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM user_info");) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                logger.info("Get all users from DB");
                result.add(new User(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3)
                ));
            }
            return result;
        }
    }

    @Override
    public Optional<User> getByLoginAndPassword(String login, String password) throws SQLException {
        User user = null;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM user_info WHERE login = ? AND password = ?");) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                logger.info("Get user by login and password: username: {}", login);

                user = new User(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3)
                );
            }
            return Optional.ofNullable(user);
        }
    }


}
