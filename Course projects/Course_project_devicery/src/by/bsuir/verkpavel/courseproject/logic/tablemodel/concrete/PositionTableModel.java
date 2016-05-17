package by.bsuir.verkpavel.courseproject.logic.tablemodel.concrete;

import java.util.List;

import by.bsuir.verkpavel.courseproject.dao.Entity;
import by.bsuir.verkpavel.courseproject.dao.entity.Position;
import by.bsuir.verkpavel.courseproject.logic.tablemodel.GeneralDeliveryServiceTableModel;

public class PositionTableModel extends GeneralDeliveryServiceTableModel {

    public PositionTableModel(List<? extends Entity> beans) {
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
        Position bean = (Position) getBeans().get(rowIndex);
        switch (columnIndex) {
        case 0:
            return bean.getIdPosition();
        case 1:
            return bean.getDescription();
        }
        return "";
    }
    
    
}
