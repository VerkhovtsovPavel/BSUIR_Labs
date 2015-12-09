package by.bsuir.verkpavel.courseproject.access.menu;

import javax.swing.JMenuBar;

import by.bsuir.verkpavel.courseproject.dao.entity.Employee;
import by.bsuir.verkpavel.courseproject.ui.change.ValueChangeView;

public abstract class BaseMenuCreator {

    private BaseMenuCreator next;
    private Employee employee;
    private JMenuBar menuBar;

    public BaseMenuCreator(BaseMenuCreator next) {
        this.next = next;
    }

    private void next() {
        if (next != null) {
            next.process(employee, menuBar);
        }
    }

    public void process(Employee employee, JMenuBar menuBar) {
        this.employee = employee;
        this.menuBar = menuBar;

        switch (employee.getPermission().getIdPermissions()) {
        case 3:
            deleteMenu(menuBar);
        case 2:
            editMenu(menuBar);
        case 1:
            showMenu(menuBar);
        }
        next();
    }

    public abstract void showMenu(JMenuBar menuBar);

    public abstract void editMenu(JMenuBar menuBar);

    public abstract void deleteMenu(JMenuBar menuBar);

    public Employee getEmployee() {
        return employee;
    }

    protected static void waitNewValue(ValueChangeView lock) {
        while (!lock.isFinish)
            ;
    }
}
