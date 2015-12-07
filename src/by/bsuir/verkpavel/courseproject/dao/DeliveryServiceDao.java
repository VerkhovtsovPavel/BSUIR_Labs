package by.bsuir.verkpavel.courseproject.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import by.bsuir.verkpavel.courseproject.resources.Messages;

public class DeliveryServiceDao {
    private static Logger log = Logger.getLogger(DeliveryServiceDao.class);
    
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
                log.info("Create dao for "+target.getSimpleName());
            } catch (SQLException e) {
                log.info("Error while create dao for "+target.getSimpleName(), e);
            }
        }
        return (Dao<T, Integer>)dao;
    }

    public static DeliveryServiceDao getInstance() {
        return instance;
    }
    
    @SuppressWarnings("unchecked")
    public <T> List<T> getAllRecord(Class<? extends Entity> target){
        Dao<? extends Entity, Integer> dao = getDaoByClass(target);
        try {
            return (List<T>)dao.queryForAll();
        } catch (SQLException e) {
            log.info("Error while get records by "+target.getSimpleName(), e);
        }
        return null;
    }
    
    public boolean addRecond(Entity entity){
        Dao<Entity, Integer> dao = getDaoByClass(entity.getClass());
        try {
            dao.create(entity);
            return true;
        } catch (SQLException e) {
            log.error(Messages.ERROR_WHILE_ADD_RECORD.get(), e);
            return false;
        }
    }

    public boolean deleteRecord(Entity deletedEntity) {
        Dao<Entity, Integer> dao = getDaoByClass(deletedEntity.getClass());
        try {
            dao.delete(deletedEntity);
            return true;
        } catch (SQLException e) {
            log.error(Messages.ERROR_WHILE_DELETE_RECORD.get(), e);
            return false;
        }
        
    }
}
