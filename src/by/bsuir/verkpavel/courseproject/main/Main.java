package by.bsuir.verkpavel.courseproject.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.bsuir.verkpavel.courseproject.dao.DeliveryServiceDao;
import by.bsuir.verkpavel.courseproject.dao.Entity;
import by.bsuir.verkpavel.courseproject.dao.entity.Employee;
import by.bsuir.verkpavel.courseproject.dao.entity.Street;
import by.bsuir.verkpavel.courseproject.ui.EntityShowView;

public class Main {

    public static void main(String[] args) throws SQLException {
        List<Entity> employees = new ArrayList<>();
        
        employees.add(new Employee());
        employees.add(new Employee());
        employees.add(new Employee());
        employees.add(new Employee());
        
        EntityShowView entityShowView = new EntityShowView(DeliveryServiceDao.getInstance().getDaoByClass(Street.class).queryForAll());
        entityShowView.showView();
    }

}
