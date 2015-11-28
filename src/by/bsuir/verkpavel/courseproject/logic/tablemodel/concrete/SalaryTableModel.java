package by.bsuir.verkpavel.courseproject.logic.tablemodel.concrete;

import java.util.List;

import by.bsuir.verkpavel.courseproject.dao.Entity;
import by.bsuir.verkpavel.courseproject.dao.entity.Salary;
import by.bsuir.verkpavel.courseproject.logic.tablemodel.GeneralDeliveryServiceTableModel;

public class SalaryTableModel extends GeneralDeliveryServiceTableModel {

    public SalaryTableModel(List<Entity> beans) {
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
            return "Оклад";
        case 2:
            return "Повышающий коэффициент";
        case 3:
            return "Итог";
        case 4:
            return "Работник";
            
        }
        return "";
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        Salary bean = (Salary) getBeans().get(rowIndex);
        switch (columnIndex) {
        case 0:
            return bean.getIdSalary();
        case 1:
            return bean.getBaseRate();
        case 2:
            return bean.getRaisingFactor();
        case 3:
            return bean.getRaisingFactor() * bean.getBaseRate();
        case 4:
            return bean.getEmployee().getFullName();     
            
        }
        return "";
    }
    
    
}
