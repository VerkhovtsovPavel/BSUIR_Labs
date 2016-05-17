package by.bsuir.verkpavel.courseproject.logic.tablemodel.concrete;

import java.util.List;

import by.bsuir.verkpavel.courseproject.dao.Entity;
import by.bsuir.verkpavel.courseproject.dao.entity.Office;
import by.bsuir.verkpavel.courseproject.logic.tablemodel.GeneralDeliveryServiceTableModel;
public class OfficeTableModel extends GeneralDeliveryServiceTableModel {

    public OfficeTableModel(List<? extends Entity> beans) {
        super(beans);
    }

    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public int getColumnCount() {
        return 5;
    }

    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
        case 0:
            return "Номер";
        case 1:
            return "Улица";
        case 2:
            return "Дом";
        case 3:
            return "Корпус";
        case 4:
            return "Комната";
        }
        return "";
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
       Office bean = (Office) getBeans().get(rowIndex);
        switch (columnIndex) {
        case 0:
            return bean.getIdOffice();
        case 1:
            return bean.getStreet().getName();
        case 2:
            return bean.getHome();
        case 3:
            return bean.getHousing();
        case 4:
            return bean.getRoom();
        }
        return "";
    }

}
