package task22.dao.mobile;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import task22.connection.ConnectionManager;
import task22.model.Mobile;

import javax.ejb.EJB;
import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@EJB
public class MobileDaoImpl implements MobileDao {
    private static final Logger logger = LogManager.getLogger(MobileDaoImpl.class.getName());

    public static final String INSERT_INTO_MOBILE = "INSERT INTO mobile values (DEFAULT, ?, ?, ?)";
    public static final String SELECT_FROM_MOBILE = "SELECT * FROM mobile WHERE id = ?";
    public static final String SELECT_ALL_FROM_MOBILE = "SELECT * FROM mobile";
    public static final String UPDATE_MOBILE = "UPDATE mobile SET model=?, price=?, manufacturer=? WHERE id=?";
    public static final String DELETE_FROM_MOBILE = "DELETE FROM mobile WHERE id=?";


    private ConnectionManager connectionManager;

    @Inject
    public MobileDaoImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Optional<Mobile> getMobileById(int id) throws SQLException {
        Mobile mobile = null;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_MOBILE)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                logger.info("Get mobile by id: {}", id);
                mobile = new Mobile(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4));
            }
            return Optional.ofNullable(mobile);
        }
    }


    @Override
    public List<Mobile> getAllMobile() throws SQLException {
        List<Mobile> lstmb = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_MOBILE);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                logger.info("Get all mobiles from DB");
                lstmb.add(new Mobile(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4)));
            }
            return lstmb;
        }
    }

    @Override
    public int addMobile(Mobile mobile) throws SQLException {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     INSERT_INTO_MOBILE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, mobile.getModel());
            preparedStatement.setInt(2, mobile.getPrice());
            preparedStatement.setString(3, mobile.getManufacturer());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                SQLException e = new SQLException("Add mobile to database: fail");
                logger.throwing(Level.ERROR, e);
                throw e;
            }

        }
    }

    @Override
    public void deleteMobileById(int id) throws SQLException {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_MOBILE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            logger.info("Delete mobile by id: {}", id);
        }

    }

    @Override
    public void updateMobileById(Mobile mobile) throws SQLException {

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MOBILE)) {
            preparedStatement.setString(1, mobile.getModel());
            preparedStatement.setInt(2, mobile.getPrice());
            preparedStatement.setString(3, mobile.getManufacturer());
            preparedStatement.setInt(4, mobile.getId());
            preparedStatement.execute();
            logger.info("Update mobile: {}", mobile);
        }

    }
}
