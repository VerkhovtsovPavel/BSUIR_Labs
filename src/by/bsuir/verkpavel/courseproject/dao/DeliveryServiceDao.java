package by.bsuir.verkpavel.courseproject.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

public class DeliveryServiceDao {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/deliveryService?useUnicode=true&characterEncoding=utf8";
    private static final String DB_USER_NAME = "root";
    private static final String DB_PASSWORD = "123456";

    private static final ConnectionSource connectionSource = createConnectionSouce();

    private static final DeliveryServiceDao instance = new DeliveryServiceDao();

    private Map<Class<? extends Entity>, Dao<? extends Entity, ?>> daos = new HashMap<>();

    private static ConnectionSource createConnectionSouce() {
        try {
            return new JdbcConnectionSource(DATABASE_URL, DB_USER_NAME, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private DeliveryServiceDao() {

    }

    @SuppressWarnings("unchecked")
    public <T> Dao<T, Integer> getDaoByClass(Class<? extends Entity> target){
        Dao<? extends Entity, ?> dao = daos.get(target);
        if (dao == null) {
            try {
                dao = DaoManager.createDao(connectionSource, target);
                daos.put(target, dao);
            } catch (SQLException e) {
            }
        }
        return (Dao<T, Integer>)dao;
    }

    public static DeliveryServiceDao getInstance() {
        return instance;
    }
}
