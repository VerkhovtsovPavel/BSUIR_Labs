package by.bsuir.verkpavel.courseproject.logic.tablemodel.concrete;

import java.util.List;

import by.bsuir.verkpavel.courseproject.dao.Entity;
import by.bsuir.verkpavel.courseproject.dao.entity.Delivery;
import by.bsuir.verkpavel.courseproject.logic.tablemodel.GeneralDeliveryServiceTableModel;

public class DeliveryTableModel extends GeneralDeliveryServiceTableModel {

    public DeliveryTableModel(List<? extends Entity> beans) {
        super(beans);
    }

    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public int getColumnCount() {
        return 8;
    }

    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
        case 0:
            return "Номер";
        case 1:
            return "Офис отправки";
        case 2:
            return "Офис доставки";
        case 3:
            return "Дата отправки";
        case 4:
            return "Дата доставки";
        case 5:
            return "Статус";
        case 6:
            return "Машина";
        case 7:
            return "Водитель";
        }
        return "";
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        Delivery bean = (Delivery) getBeans().get(rowIndex);
        switch (columnIndex) {
        case 0:
            return bean.getIdDelivery();
        case 1:
            return bean.getFromOffice();
        case 2:
            return bean.getToOffice();
        case 3:
            return bean.getStartDate();
        case 4:
            return bean.getEndDate();
        case 5:
            return bean.getDeliveryStatus().getDescription();
        case 6:
            return bean.getCorporateCar().getNumber();
        case 7:
            return bean.getDriver().getEmployee().getFullName();
        }
        return "";
    }
    
    
}
