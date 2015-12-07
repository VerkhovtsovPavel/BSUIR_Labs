package by.bsuir.verkpavel.courseproject.logic.tablemodel;

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
    
    //TODO Make abstract or remove implementation
    public void processClick(int row, int column) {
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

    public final void processDelete(int selectedRow, int selectedColumns) {
        Entity deletedEntity = _beans.get(selectedRow);
        DeliveryServiceDao.getInstance().deleteRecord(deletedEntity);
    }
}
