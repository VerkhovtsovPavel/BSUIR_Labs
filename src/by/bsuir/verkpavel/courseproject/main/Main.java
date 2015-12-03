package by.bsuir.verkpavel.courseproject.main;

import java.sql.SQLException;

import by.bsuir.verkpavel.courseproject.dao.DeliveryServiceDao;
import by.bsuir.verkpavel.courseproject.dao.entity.Client;
import by.bsuir.verkpavel.courseproject.ui.EntityShowView;
import by.bsuir.verkpavel.courseproject.ui.add.AddClientView;

import com.j256.ormlite.dao.Dao;

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
        
        AddClientView addClientView = new AddClientView();
        addClientView.showView();
        
        Dao<Client, Integer> streetDao = DeliveryServiceDao.getInstance().getDaoByClass(Client.class);
        EntityShowView entityShowView = new EntityShowView(streetDao.queryForAll());
        entityShowView.showView();

    }
}
