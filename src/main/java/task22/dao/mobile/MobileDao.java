package task22.dao.mobile;


import task22.model.Mobile;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface MobileDao {

    Optional<Mobile> getMobileById(int id) throws SQLException;

    int addMobile(Mobile mobile) throws SQLException;

    void deleteMobileById(int id) throws SQLException;

    List<Mobile> getAllMobile() throws SQLException;

    void updateMobileById(Mobile mobile) throws SQLException;
}
