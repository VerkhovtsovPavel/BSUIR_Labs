package by.bsuir.verkpavel.courseproject.logic.tablemodel.concrete;

import java.util.List;

import by.bsuir.verkpavel.courseproject.dao.Entity;
import by.bsuir.verkpavel.courseproject.dao.entity.Rate;
import by.bsuir.verkpavel.courseproject.logic.tablemodel.GeneralDeliveryServiceTableModel;

public class RateTableModel extends GeneralDeliveryServiceTableModel {

    public RateTableModel(List<? extends Entity> beans) {
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
            return "Номер п.п";
        case 1:
            return "Стоимость (высота)";
        case 2:
            return "Стоимость (ширина)";
        case 3:
            return "Стоимость (глубина)";
        case 4:
            return "Стоимость (вес)";
        }
        return "";
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        Rate bean = (Rate) getBeans().get(rowIndex);
        switch (columnIndex) {
        case 0:
            return bean.getIdRate();
        case 1:
            return bean.getHeigth();
        case 2:
            return bean.getWidth();
        case 3:
            return bean.getDepth();
        case 4:
            return bean.getWeigth();
        }
        return "";
    }
    
    
}
