package by.bsuir.verkpavel.courseproject.logic.tablemodel.concrete;

import java.util.List;

import by.bsuir.verkpavel.courseproject.dao.Entity;
import by.bsuir.verkpavel.courseproject.dao.entity.Parcel;
import by.bsuir.verkpavel.courseproject.logic.tablemodel.GeneralDeliveryServiceTableModel;

public class ParcelTableModel extends GeneralDeliveryServiceTableModel {

    public ParcelTableModel(List<? extends Entity> beans) {
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
            return "Номер";
        case 1:
            return "Высота";
        case 2:
            return "Ширина";
        case 3:
            return "Глибина";
        case 4: 
            return "Вес";
        case 5:
            return "Клиент";
        case 6:
            return "Отметка";
        case 7:
            return "Стоимость пересылки";
        case 8:
            return "Способ платежа";
        case 9:
            return "Дата оплаты";
            
                    
        }
        return "";
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        Parcel bean = (Parcel) getBeans().get(rowIndex);
        switch (columnIndex) {
        case 0:
            return bean.getIdParcel();
        case 1:
            return bean.getHeight();
        case 2:
            return bean.getWidth();
        case 3:
            return bean.getDepth();
        case 4:
            return bean.getWeight();
        case 5:
            return bean.getClient().getFullName();
        case 6:
            return bean.getMarkParcel().getDescription();
        case 7:
            return bean.getPayment().getSum();
        case 8:
            return bean.getPayment().getPayDate();
        case 9:
            return bean.getDepth();
        }
        return "";
    }
    
    
}
