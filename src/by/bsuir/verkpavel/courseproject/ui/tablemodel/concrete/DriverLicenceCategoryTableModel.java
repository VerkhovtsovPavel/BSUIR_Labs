package by.bsuir.verkpavel.courseproject.ui.tablemodel.concrete;

import java.util.List;

import by.bsuir.verkpavel.courseproject.dao.Entity;
import by.bsuir.verkpavel.courseproject.dao.entity.DriverLicenceCategory;
import by.bsuir.verkpavel.courseproject.ui.tablemodel.GeneralDeliveryServiceTableModel;

public class DriverLicenceCategoryTableModel extends GeneralDeliveryServiceTableModel {

    public DriverLicenceCategoryTableModel(List<Entity> beans) {
        super(beans);
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
        case 0:
            return "Номер";
        case 1:
            return "Обозначение";
        }
        return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DriverLicenceCategory bean = (DriverLicenceCategory) getBeans().get(rowIndex);
        switch (columnIndex) {
        case 0:
            return bean.getIdDriverLicenceCategory();
        case 1:
            return bean.getDescription();
        }
        return "";

    }

}
