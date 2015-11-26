package by.bsuir.verkpavel.courseproject.ui.tablemodel.concrete;

import java.util.List;

import by.bsuir.verkpavel.courseproject.dao.Entity;
import by.bsuir.verkpavel.courseproject.dao.entity.CorporateCar;
import by.bsuir.verkpavel.courseproject.ui.tablemodel.GeneralDeliveryServiceTableModel;

public class CorporateCarTableModel extends GeneralDeliveryServiceTableModel {

    public CorporateCarTableModel(List<Entity> beans) {
        super(beans);
    }

    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public int getColumnCount() {
        return 10;
    }

    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
        case 0:
            return "Номер п.п";
        case 1:
            return "Марка";
        case 2:
            return "Номер гос. регистрации";
        case 3:
            return "Дата покупки";
        case 4:
            return "Вместительность(высота)";
        case 5:
            return "Вместительность(ширина)";
        case 6:
            return "Вместительность(глубина)";
        case 7:
            return "Вместительность(вес)";
        case 8:
            return "Необходимая категория прав";
        case 9:
            return "Текущий офис";
            
        }
        return "";
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        CorporateCar bean = (CorporateCar) getBeans().get(rowIndex);
        switch (columnIndex) {
        case 0:
            return bean.getIdCorporateCar();
        case 1:
            return bean.getMark();
        case 2:
            return bean.getNumber();
        case 3:
            return bean.getBuyDate();
        case 4:
            return bean.getMaxHeight();
        case 5:
            return bean.getMaxWidth();
        case 6:
            return bean.getMaxDepth();
        case 7:
            return bean.getMaxWeigth();
        case 8:
            return bean.getDriverlicencecategory().getDescription();
        case 9:
            return bean.getOffice() == null ? "В дороге" : bean.getOffice();    
        }
        return "";
    }
    
    
}
