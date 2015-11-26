package by.bsuir.verkpavel.courseproject.ui.tablemodel.concrete;

import java.util.List;

import by.bsuir.verkpavel.courseproject.dao.Entity;
import by.bsuir.verkpavel.courseproject.dao.entity.Client;
import by.bsuir.verkpavel.courseproject.ui.tablemodel.GeneralDeliveryServiceTableModel;

public class ClientTableModel extends GeneralDeliveryServiceTableModel {

    public ClientTableModel(List<Entity> beans) {
        super(beans);
    }

    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public int getColumnCount() {
        return 3;
    }

    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
        case 0:
            return "Номер";
        case 1:
            return "Ф.И.О.";
        case 2:
            return "День первого обращения";
        }
        return "";
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        Client bean = (Client) getBeans().get(rowIndex);
        switch (columnIndex) {
        case 0:
            return bean.getIdClient();
        case 1:
            return bean.getFullName();
        case 2:
            return bean.getAddDate();
        }
        return "";
    }
    
    
}
