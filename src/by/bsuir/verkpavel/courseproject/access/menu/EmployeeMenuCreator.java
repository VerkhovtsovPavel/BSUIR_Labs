package by.bsuir.verkpavel.courseproject.access.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
                // TODO Go to login view and delete all session data
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
                // TODO Use value change view send old userName
            }
        });

        employeelMenu.add(addEmployee);
    }

    @Override
    public void deleteMenu(JMenuBar menuBar) {
        JMenuItem fireEmployee = new JMenuItem("Уволить работника");
        fireEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Use value change view send "Старый пароль"
            }
        });
        employeelMenu.add(fireEmployee);
    }
}
