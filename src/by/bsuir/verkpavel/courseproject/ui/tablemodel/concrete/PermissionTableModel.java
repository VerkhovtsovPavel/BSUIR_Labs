package by.bsuir.verkpavel.courseproject.ui.tablemodel.concrete;

import java.util.List;

import by.bsuir.verkpavel.courseproject.dao.Entity;
import by.bsuir.verkpavel.courseproject.dao.entity.Permission;
import by.bsuir.verkpavel.courseproject.ui.tablemodel.GeneralDeliveryServiceTableModel;

public class PermissionTableModel extends GeneralDeliveryServiceTableModel {

    public PermissionTableModel(List<Entity> beans) {
        super(beans);
    }

    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public int getColumnCount() {
        return 2;
    }

    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
        case 0:
            return "Номер";
        case 1:
            return "Описание";
        }
        return "";
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        Permission bean = (Permission) getBeans().get(rowIndex);
        switch (columnIndex) {
        case 0:
            return bean.getIdPermissions();
        case 1:
            return bean.getDescription();
        }
        return "";
    }
    
    
}
