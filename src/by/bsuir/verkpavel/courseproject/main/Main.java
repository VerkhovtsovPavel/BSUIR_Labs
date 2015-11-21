package by.bsuir.verkpavel.courseproject.main;

import java.util.ArrayList;
import java.util.List;

import by.bsuir.verkpavel.courseproject.dao.Entity;
import by.bsuir.verkpavel.courseproject.sample.model.Employee;
import by.bsuir.verkpavel.courseproject.ui.EntityShowView;

public class Main {

    public static void main(String[] args) {
        List<Entity> employees = new ArrayList<>();
        
        employees.add(new Employee());
        employees.add(new Employee());
        employees.add(new Employee());
        employees.add(new Employee());
        
        EntityShowView entityShowView = new EntityShowView(employees);
        
    }

}
