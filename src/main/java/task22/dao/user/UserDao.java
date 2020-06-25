package task22.dao.user;



import task22.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


/**
 * UserInfoDao
 * интерфейс CRUD-операций с объектами User (пользователи)
 *
 * created by Ksenya_Ushakova at 31.05.2020
 */
public interface UserDao {
    String addUser(User user) throws SQLException;
    Optional<User> getByLogin(String login) throws SQLException;
    void updateByLogin(User user) throws SQLException;
    void deleteByLogin(String login) throws SQLException;

    List<User> getAllUsers() throws SQLException;

    Optional<User> getByLoginAndPassword(String login, String password) throws SQLException;
}
