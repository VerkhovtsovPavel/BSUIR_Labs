package by.bsuir.verkpavel.courseproject.logic.tablemodel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.apache.log4j.Logger;

import by.bsuir.verkpavel.courseproject.dao.DeliveryServiceDao;
import by.bsuir.verkpavel.courseproject.dao.Entity;
import by.bsuir.verkpavel.courseproject.dao.entity.Authentication;
import by.bsuir.verkpavel.courseproject.dao.entity.Driver;
import by.bsuir.verkpavel.courseproject.dao.entity.Employee;

public abstract class GeneralDeliveryServiceTableModel implements TableModel {

    private static final Logger log = Logger.getLogger(GeneralDeliveryServiceTableModel.class);

    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

    private List<? extends Entity> _beans;

    public GeneralDeliveryServiceTableModel(List<? extends Entity> beans) {
        this._beans = beans;
    }

    public void addTableModelListener(TableModelListener listener) {
        listeners.add(listener);
    }

    public int getRowCount() {
        return _beans.size();
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void removeTableModelListener(TableModelListener listener) {
        listeners.remove(listener);
    }

    public void setValueAt(Object value, int rowIndex, int columnIndex) {

    }

    public List<? extends Entity> getBeans() {
        return _beans;
    }

    public void processClick(int row, int column) {
        log.debug("Default haldler");
        String selectedData = getValueAt(row, column).toString();
        log.debug(String.format("Selected: row %d, colum %d, value %s ", row, column, selectedData));
    };

    public final boolean processDelete(int selectedRow, int selectedColumns) {
        Entity deletedEntity = _beans.get(selectedRow);
        Method[] methods = deletedEntity.getClass().getMethods();
        boolean useUpdate = false;
        for (Method method : methods) {
            if (method.getName().equals("setIsActive")) {
                useUpdate = true;
                callMethod(method, deletedEntity, 0);
            }
        }
        if (useUpdate) {
            if(deletedEntity.getClass().getSimpleName().equals("Employee")){
                List<Driver> drivers = DeliveryServiceDao.getInstance().getAllRecord(Driver.class);
                Driver deletedDriver = null;
                for(Driver driver : drivers){
                   if(driver.getEmployee().getIdEmployee()== ((Employee)deletedEntity).getIdEmployee()){
                       deletedDriver = driver;
                       break;
                   }
                }
                    deletedDriver.setIsActive(0);
                    DeliveryServiceDao.getInstance().updateRecord(deletedDriver);
                    Authentication authentication = ((Employee)deletedEntity).getAuthentication();
                    authentication.setPassword("11111111");
                    DeliveryServiceDao.getInstance().updateRecord(authentication);
                }
           return DeliveryServiceDao.getInstance().updateRecord(deletedEntity);
        } else {
            return DeliveryServiceDao.getInstance().deleteRecord(deletedEntity);
        }
    }

    private void callMethod(Method method, Object deletedEntity, int value) {
            try {
                method.invoke(deletedEntity, value);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                log.error("Error while try set value in field", e);
            }
    }
}
