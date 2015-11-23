package by.bsuir.verkpavel.courseproject.ui.tablemodel.concrete;

import java.util.List;

import by.bsuir.verkpavel.courseproject.dao.Entity;
import by.bsuir.verkpavel.courseproject.dao.entity.Street;
import by.bsuir.verkpavel.courseproject.ui.tablemodel.GeneralDeliveryServiceTableModel;

public class StreetTableModel  extends GeneralDeliveryServiceTableModel{

    public StreetTableModel(List<Entity> beans) {
        super(beans);
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
        case 0:
            return "Номер";
        case 1:
            return "Улица";
        case 2:
            return "Город";
        }
        return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Street bean = (Street) getBeans().get(rowIndex);
        switch (columnIndex) {
        case 0:
            return bean.getIdStreet();
        case 1:
            return bean.getName();
        case 2:
            return bean.getCity().getName();
        }
        return "";
    }

}
