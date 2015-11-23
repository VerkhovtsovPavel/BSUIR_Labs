package by.bsuir.verkpavel.courseproject.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

public class DeliveryServiceDao {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/bank_users?useUnicode=true&characterEncoding=utf8";
    private static final String DB_USER_NAME = "root";
    private static final String DB_PASSWORD = "123456";

    private static final ConnectionSource connectionSource = createConnectionSouce();

    private Map<Class<?>, Dao<?, ?>> daos = new HashMap<>();

    private static ConnectionSource createConnectionSouce() {
        try {
            return new JdbcConnectionSource(DATABASE_URL, DB_USER_NAME, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public DeliveryServiceDao() {
        try {
            daos = DAOUtil.getDAOs(connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<?, ?> getDaoByClass(Class<?> target) {
        return daos.get(target);
    }
}
