package by.bsuir.verkpavel.courseproject.access.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import by.bsuir.verkpavel.courseproject.dao.DeliveryServiceDao;
import by.bsuir.verkpavel.courseproject.dao.entity.Employee;
import by.bsuir.verkpavel.courseproject.ui.EntityShowView;
import by.bsuir.verkpavel.courseproject.ui.add.AddEmployeeView;

public class EmployeeMenuCreator extends BaseMenuCreator {

    private JMenu employeelMenu;

    public EmployeeMenuCreator(BaseMenuCreator next) {
        super(next);
        employeelMenu = new JMenu("Работники");
    }

    @Override
    public void showMenu(JMenuBar menuBar) {
        JMenuItem showEmployee = new JMenuItem("Просмотреть работников");
        showEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Employee> entity = DeliveryServiceDao.getInstance().getAllRecord(Employee.class);
                EntityShowView entityShowView = new EntityShowView(entity);
                entityShowView.showView();
            }
        });
        employeelMenu.add(showEmployee);

        menuBar.add(employeelMenu);
    }

    @Override
    public void editMenu(JMenuBar menuBar) {
        JMenuItem addEmployee = new JMenuItem("Добавить работника");
        addEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddEmployeeView addEmployeeView = new AddEmployeeView();
                addEmployeeView.showView();
            }
        });

        employeelMenu.add(addEmployee);
    }

    @Override
    public void deleteMenu(JMenuBar menuBar) {
        JMenuItem fireEmployee = new JMenuItem("Уволить работника");
        fireEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Employee> entity = DeliveryServiceDao.getInstance().getAllRecord(Employee.class);
                EntityShowView entityShowView = new EntityShowView(entity, true);
                entityShowView.showView();
            }
        });
        employeelMenu.add(fireEmployee);
    }
}
