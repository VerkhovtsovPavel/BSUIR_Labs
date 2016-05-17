package by.bsuir.verkpavel.courseproject.logic.tablemodel.concrete;

import java.util.ArrayList;
import java.util.List;

import by.bsuir.verkpavel.courseproject.dao.Entity;
import by.bsuir.verkpavel.courseproject.dao.entity.Street;
import by.bsuir.verkpavel.courseproject.logic.tablemodel.GeneralDeliveryServiceTableModel;
import by.bsuir.verkpavel.courseproject.ui.EntityShowView;

public class StreetTableModel extends GeneralDeliveryServiceTableModel {

    public StreetTableModel(List<? extends Entity> beans) {
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

    @Override
    public void processClick(int row, int column) {
        if (column == 2) {
            String cityName = getValueAt(row, column).toString();
            ArrayList<Street> streetInCity = new ArrayList<Street>();
            for (Entity street : getBeans()) {
                if (((Street) street).getCity().getName().equals(cityName)) {
                    streetInCity.add((Street) street);
                }
            }
            new EntityShowView(streetInCity).showView();
        }
    }

}
