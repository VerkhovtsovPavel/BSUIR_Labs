package by.bsuir.verkpavel.courseproject.logic.tablemodel.concrete;

import java.util.List;

import by.bsuir.verkpavel.courseproject.dao.Entity;
import by.bsuir.verkpavel.courseproject.dao.entity.Employee;
import by.bsuir.verkpavel.courseproject.logic.tablemodel.GeneralDeliveryServiceTableModel;
import by.bsuir.verkpavel.courseproject.resources.ProjectProperties;

public class EmployeeTableModel extends GeneralDeliveryServiceTableModel {

    public EmployeeTableModel(List<? extends Entity> beans) {
        super(beans);
    }

    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public int getColumnCount() {
        return 7;
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
        case 4:
            return "Номер телефона";
        case 5:
            return "Email";
        case 6:
            return "Должность";     
            
        }
        return "";
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee bean = (Employee) getBeans().get(rowIndex);
        switch (columnIndex) {
        case 0:
            return bean.getFullName();
        case 1:
            return bean.getOffice().getDescription();
        case 2:
            return ProjectProperties.getDateFormatter().format(bean.getBirthday());
        case 3:
            return ProjectProperties.getDateFormatter().format(bean.getHireDate());
        case 4:
            return bean.getPhoneNumber();
        case 5:
            return bean.geteMail();
        case 6:
            return bean.getPosition().getDescription();
        }
        return "";
    }
    
    
}
