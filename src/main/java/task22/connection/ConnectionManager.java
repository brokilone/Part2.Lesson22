package task22.connection;

import javax.ejb.EJB;
import java.sql.Connection;

/**
 * ConnectionManager
 * интерфейс получения соединения с БД
 * created by Ksenya_Ushakova at 31.05.2020
 */
@EJB
public interface ConnectionManager {
    Connection getConnection();
}
