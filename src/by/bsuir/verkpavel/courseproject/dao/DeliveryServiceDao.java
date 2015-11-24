package by.bsuir.verkpavel.courseproject.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

public class DeliveryServiceDao {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/deliveryservice?useUnicode=true&characterEncoding=utf8";
    private static final String DB_USER_NAME = "root";
    private static final String DB_PASSWORD = "123456";

    private static final ConnectionSource connectionSource = createConnectionSouce();
    
    private static final DeliveryServiceDao instance = new DeliveryServiceDao();

    private Map<Class<?>, Dao<Entity, ?>> daos = new HashMap<>();

    private static ConnectionSource createConnectionSouce() {
        try {
            return new JdbcConnectionSource(DATABASE_URL, DB_USER_NAME, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private DeliveryServiceDao() {
        try {
            this.daos = createDAOsMap(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<Entity, ?> getDaoByClass(Class<?> target) {
        return daos.get(target);
    }
    
    public static DeliveryServiceDao getInstance(){
        return instance;
    }
    
    private static  Map<Class<?>,Dao<Entity,?>> createDAOsMap() throws SQLException{
        List<Class<?>> classes = DAOUtil.getClassesForPackage("by.bsuir.verkpavel.courseproject.dao.entity");
        HashMap<Class<?>, Dao<Entity,?>> DAOs = new HashMap<>();
        
        for(Class<?> c : classes){
            DAOs.put(c, (Dao<Entity, ?>) DaoManager.createDao(connectionSource, c));
        } 
        return DAOs;
    }
}
