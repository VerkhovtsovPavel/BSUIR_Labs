package by.bsuir.verkpavel.courseproject.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import by.bsuir.verkpavel.courseproject.dao.entity.Authentication;
import by.bsuir.verkpavel.courseproject.dao.entity.Delivery;
import by.bsuir.verkpavel.courseproject.dao.entity.DeliveryStatus;
import by.bsuir.verkpavel.courseproject.dao.entity.Employee;
import by.bsuir.verkpavel.courseproject.dao.entity.ParcelM2MDelivery;
import by.bsuir.verkpavel.courseproject.resources.Messages;

public class DeliveryServiceDao {
    private static Logger log = Logger.getLogger(DeliveryServiceDao.class);

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/deliveryService?useUnicode=true&characterEncoding=utf8";
    private static final String DB_USER_NAME = "root";
    private static final String DB_PASSWORD = "123456";

    private static final ConnectionSource connectionSource = createConnectionSouce();

    private static final DeliveryServiceDao instance = new DeliveryServiceDao();

    private Map<Class<? extends Entity>, Dao<? extends Entity, ?>> daos = new HashMap<Class<? extends Entity>, Dao<? extends Entity, ?>>();

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

    public static DeliveryServiceDao getInstance() {
        return instance;
    }

    @SuppressWarnings("unchecked")
    public <T> Dao<T, Integer> getDaoByClass(Class<? extends Entity> target) {
        Dao<? extends Entity, ?> dao = daos.get(target);
        if (dao == null) {
            try {
                dao = DaoManager.createDao(connectionSource, target);
                daos.put(target, dao);
                log.info("Create dao for " + target.getSimpleName());
            } catch (SQLException e) {
                log.info("Error while create dao for " + target.getSimpleName(), e);
            }
        }
        return (Dao<T, Integer>) dao;
    }
    
    public DeliveryStatus getDeliveryStatusByDescription(String description){
        QueryBuilder<DeliveryStatus, Integer> statementBuilder = DeliveryServiceDao.getInstance()
                .getQueryBuilderByClass(DeliveryStatus.class);
        try {
            statementBuilder.where().eq("description", description);
        } catch (SQLException e) {
            log.info("Error while get authentication", e);
        }
        List<DeliveryStatus> deliveryStatus =  DeliveryServiceDao.getInstance().getExeciteQuery(statementBuilder, DeliveryStatus.class);
        return !deliveryStatus.isEmpty() ? deliveryStatus.get(0) : null;
    }
    
    public List<Delivery> getDeliveryByStatus(DeliveryStatus status){
        QueryBuilder<Delivery, Integer> statementBuilder = DeliveryServiceDao.getInstance()
                .getQueryBuilderByClass(Delivery.class);
        try {
            statementBuilder.where().eq("idDeliveryStatus", status.getIdDeliveryStatus());
        } catch (SQLException e) {
            log.info("Error while get authentication", e);
        }
        return DeliveryServiceDao.getInstance().getExeciteQuery(statementBuilder, Delivery.class);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getAllRecord(Class<? extends Entity> target) {
        Dao<? extends Entity, Integer> dao = getDaoByClass(target);
        try {
            return (List<T>) dao.queryForAll();
        } catch (SQLException e) {
            log.info("Error while get records by " + target.getSimpleName(), e);
        }
        return null;
    }
    

    public List<Authentication> getAuthentication(String login, String password) {
        QueryBuilder<Authentication, Integer> statementBuilder = DeliveryServiceDao.getInstance()
                .getQueryBuilderByClass(Authentication.class);
        try {
            statementBuilder.where().eq("userName", login).and().eq("password", password);
        } catch (SQLException e) {
            log.info("Error while get authentication", e);
        }
        return DeliveryServiceDao.getInstance().getExeciteQuery(statementBuilder, Authentication.class);
    }
    
    @SuppressWarnings("unchecked")
    public <T> List<T> getActiveRecords(Class<? extends Entity> target) {
        QueryBuilder<? extends Entity, Integer> statementBuilder = DeliveryServiceDao.getInstance()
                .getQueryBuilderByClass(target);
        try {
            statementBuilder.where().eq("isActive", 1);
        } catch (SQLException e) {
            log.info("Error while get authentication", e);
        }
        return (List<T>) DeliveryServiceDao.getInstance().getExeciteQuery(statementBuilder, target);
    }

    @SuppressWarnings("unchecked")
    public <T> T getRecordById(int id, Class<? extends Entity> target) {
        Dao<? extends Entity, Integer> dao = getDaoByClass(target);
        try {
            return (T) dao.queryForId(id);
        } catch (SQLException e) {
            log.info("Error while get records by " + target.getSimpleName(), e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> QueryBuilder<T, Integer> getQueryBuilderByClass(Class<? extends Entity> target) {
        return (QueryBuilder<T, Integer>) getDaoByClass(target).queryBuilder();
    }

    public boolean refreshRecord(Entity entity) {
        Dao<Entity, Integer> dao = getDaoByClass(entity.getClass());
        try {
            dao.refresh(entity);
            return true;
        } catch (SQLException e) {
            log.error(Messages.ERROR_WHILE_ADD_RECORD.get(), e);
            return false;
        }
    }

    public <T> List<T> getExeciteQuery(QueryBuilder<T, Integer> query, Class<? extends Entity> target) {
        Dao<T, Integer> dao = getDaoByClass(target);
        try {
            return (List<T>) dao.query(query.prepare());
        } catch (SQLException e) {
            log.info("Error while get records by " + target.getSimpleName(), e);
        }
        return null;
    }

    public boolean addRecord(Entity entity) {
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

    public boolean updateRecord(Entity deletedEntity) {
        Dao<Entity, Integer> dao = getDaoByClass(deletedEntity.getClass());
        try {
            dao.update(deletedEntity);
            return true;
        } catch (SQLException e) {
            log.error(Messages.ERROR_WHILE_UPDATE_RECORD.get(), e);
            return false;
        }

    }

    public boolean deleteParcelFromDelivery(ParcelM2MDelivery parcelM2MDelivery) {
        Dao<ParcelM2MDelivery, Integer> dao = getDaoByClass(ParcelM2MDelivery.class);
        try {
            DeleteBuilder<ParcelM2MDelivery, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().eq("idParcel", parcelM2MDelivery.getParcel().getIdParcel()).and().eq("idDelivery", parcelM2MDelivery.getDelivery().getIdDelivery());
            dao.delete(deleteBuilder.prepare());
            return true;
        } catch (SQLException e) {
            log.error(Messages.ERROR_WHILE_DELETE_RECORD.get(), e);
            return false;
        }
        
    }

	public List<Employee> getDrivers() {
		Dao<Employee, Integer> dao = getDaoByClass(Employee.class);
        try {
            QueryBuilder<Employee, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().eq("idPosition", 7);
            return dao.query(queryBuilder.prepare());
        } catch (SQLException e) {
            log.error(Messages.ERROR_WHILE_DELETE_RECORD.get(), e);
            return null;
        }
	}
}
