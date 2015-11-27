package by.bsuir.verkpavel.courseproject.ui.tablemodel;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import by.bsuir.verkpavel.courseproject.dao.Entity;


public abstract class GeneralDeliveryServiceTableModel implements TableModel {

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
    
    public void processClick(int row, int column){
        String selectedData = getValueAt(row, column).toString();
        System.out.println("Selected: " + selectedData);
    };
}
