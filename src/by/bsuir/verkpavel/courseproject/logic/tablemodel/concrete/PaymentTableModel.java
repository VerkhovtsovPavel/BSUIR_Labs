package by.bsuir.verkpavel.courseproject.logic.tablemodel.concrete;

import java.util.List;

import by.bsuir.verkpavel.courseproject.dao.Entity;
import by.bsuir.verkpavel.courseproject.dao.entity.Payment;
import by.bsuir.verkpavel.courseproject.logic.tablemodel.GeneralDeliveryServiceTableModel;

public class PaymentTableModel extends GeneralDeliveryServiceTableModel {

    public PaymentTableModel(List<? extends Entity> beans) {
        super(beans);
    }

    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public int getColumnCount() {
        return 4;
    }

    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
        case 0:
            return "Номер";
        case 1:
            return "Cумма";
        case 2: 
            return "Способ оплаты";
        case 3:
            return "Дата платежа";
        }
        return "";
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        Payment bean = (Payment) getBeans().get(rowIndex);
        switch (columnIndex) {
        case 0:
            return bean.getIdPayments();
        case 1:
            return bean.getSum();
        case 2:    
            return bean.getPaymentsSystemType().getDescription();
        case 3:
            return bean.getPayDate();
        }
        return "";
    }

}
