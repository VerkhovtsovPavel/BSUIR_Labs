package by.bsuir.verkpavel.courseproject.main;

import java.sql.SQLException;

import by.bsuir.verkpavel.courseproject.dao.entity.Employee;
import by.bsuir.verkpavel.courseproject.dao.entity.Permission;
import by.bsuir.verkpavel.courseproject.ui.MainView;

public class Main {

    public static void main(String[] args) throws SQLException {
        // List<Entity> employees = new ArrayList<>();
        //
        // employees.add(new Employee());
        // employees.add(new Employee());
        // employees.add(new Employee());
        // employees.add(new Employee());
        //
        // Dao<Street, Integer> streetDao = DeliveryServiceDao.getInstance().getDaoByClass(Street.class);
        // EntityShowView entityShowView = new EntityShowView(streetDao.queryForAll());
        //
        // entityShowView.showView();
        //
        // MainView mainView = new MainView();
        // mainView.showView();
//        Object lock = new Object();
//        ValueChangeView changeView = new ValueChangeView(23, lock);
//        waitNewValue(lock);
//        String newValue = changeView.getNewValue();
//        System.out.println(newValue);
        
//        AddEmployeeView addEmployeeView  = new AddEmployeeView();
//        addEmployeeView.showView();
        Employee tempEmployee = new Employee();
        Permission permission = new Permission();
        permission.setIdPermissions(3);
        tempEmployee.setPermission(permission);
        MainView mainView = new MainView(tempEmployee);
        mainView.showView();

    }
}
