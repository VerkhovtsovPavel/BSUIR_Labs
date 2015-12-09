package by.bsuir.verkpavel.courseproject.logic.tablemodel;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.apache.log4j.Logger;

import by.bsuir.verkpavel.courseproject.dao.DeliveryServiceDao;
import by.bsuir.verkpavel.courseproject.dao.Entity;

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

    protected static void waitNewValue(Object lock) {
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                log.error("Error while wait on monitor", e);
            }
        }
    }

    public final boolean processDelete(int selectedRow, int selectedColumns) {
        Entity deletedEntity = _beans.get(selectedRow);
        Field[] fields = deletedEntity.getClass().getFields();
        boolean useUpdate = false;
        for (Field field : fields) {
            if (field.getName().equals("isActive")) {
                useUpdate = true;
                setFielsValue(field, deletedEntity, true);
            }
        }
        if (useUpdate) {
           return DeliveryServiceDao.getInstance().updateRecord(deletedEntity);
        } else {
            return DeliveryServiceDao.getInstance().deleteRecord(deletedEntity);
        }
    }

    private void setFielsValue(Field field, Object deletedEntity, boolean value) {
        try {
            field.setBoolean(deletedEntity, value);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            log.error("Error while try set value in field", e);
        }
    }
}
