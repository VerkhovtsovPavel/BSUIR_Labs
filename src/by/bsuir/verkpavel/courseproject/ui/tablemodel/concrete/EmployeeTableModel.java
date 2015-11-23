package by.bsuir.verkpavel.courseproject.ui.tablemodel.concrete;

import java.util.List;

import by.bsuir.verkpavel.courseproject.dao.Entity;
import by.bsuir.verkpavel.courseproject.dao.entity.Employee;
import by.bsuir.verkpavel.courseproject.ui.tablemodel.GeneralDeliveryServiceTableModel;

public class EmployeeTableModel extends GeneralDeliveryServiceTableModel {

    public EmployeeTableModel(List<Entity> beans) {
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
            return "Ф.И.О";
        case 1:
            return "Офис";
        case 2:
            return "День рождения";
        case 3:
            return "Дата приема на работу";
        }
        return "";
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee bean = (Employee) getBeans().get(rowIndex);
        switch (columnIndex) {
        case 0:
            return bean.getFullName();
        case 1:
            return bean.getOffice();
        case 2:
            return bean.getBirthday();
        case 3:
            return bean.getHireDate();
        }
        return "";
    }
}
