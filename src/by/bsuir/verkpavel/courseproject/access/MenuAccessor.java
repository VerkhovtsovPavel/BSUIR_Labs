package by.bsuir.verkpavel.courseproject.access;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import by.bsuir.verkpavel.courseproject.dao.entity.Employee;

public class MenuAccessor {

    public static JMenuBar createMenu(Employee currentUser) {
        JMenuBar menuBar = new JMenuBar();
        createPersonalMenu(menuBar, currentUser);
        createEmployeeMenu(menuBar, currentUser);
        createUsersMenu(menuBar, currentUser);
        createDeliveryMenu(menuBar, currentUser);
        createParcelMenu(menuBar, currentUser);
        createCorporateCarMenu(menuBar, currentUser);
        return menuBar;
    }

    private static void createPersonalMenu(JMenuBar menuBar, Employee currentUser) {
        JMenu personalMenu = new JMenu("Мой профиль");
        JMenuItem changeLogin = new JMenuItem("Изменить логин");
        changeLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Use value change view send old userName
            }
        });

        JMenuItem changePassword = new JMenuItem("Изменить пароль");
        changePassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        JMenuItem logOut = new JMenuItem("Выйти из учетной записи");
        logOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Go to login view and delete all session data
            }
        });

        personalMenu.add(changeLogin);
        personalMenu.add(changePassword);
        personalMenu.add(logOut);

        menuBar.add(personalMenu);
    }

    private static void createCorporateCarMenu(JMenuBar menuBar, Employee currentUser) {
        JMenu personalMenu = new JMenu("Служебные машины");
        JMenuItem addCar = new JMenuItem("Добавить машины");
        addCar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
        });

        JMenuItem cancelСar = new JMenuItem("Списать машины");
        cancelСar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
        });

        JMenuItem showCar = new JMenuItem("Просмотреть работников");
        showCar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
        });

        personalMenu.add(addCar);
        personalMenu.add(cancelСar);
        personalMenu.add(showCar);

        menuBar.add(personalMenu);
    }

    private static void createParcelMenu(JMenuBar menuBar, Employee currentUser) {
        JMenu personalMenu = new JMenu("Посылки");
        JMenuItem addParcel = new JMenuItem("Добавить посылку");
        addParcel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
        });

        JMenuItem deleteParcel = new JMenuItem("Удалить посылку");
        deleteParcel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
        });

        JMenuItem showParcel = new JMenuItem("Просмотреть посылки");
        showParcel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
        });

        personalMenu.add(addParcel);
        personalMenu.add(deleteParcel);
        personalMenu.add(showParcel);

        menuBar.add(personalMenu);
    }

    private static void createDeliveryMenu(JMenuBar menuBar, Employee currentUser) {
        JMenu personalMenu = new JMenu("Доставки");
        JMenuItem addDelivery = new JMenuItem("Создать доставку");
        addDelivery.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
        });

        JMenuItem addParcelToDelivery = new JMenuItem("Добавить товар в достаку");
        addParcelToDelivery.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
        });

        JMenuItem deleteParcelToDelivery = new JMenuItem("Удалить товар в достаку");
        deleteParcelToDelivery.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
        });

        JMenuItem showDelivery = new JMenuItem("Просмотреть доставки");
        showDelivery.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
        });

        personalMenu.add(addDelivery);
        personalMenu.add(addParcelToDelivery);
        personalMenu.add(showDelivery);

        menuBar.add(personalMenu);
    }

    private static void createUsersMenu(JMenuBar menuBar, Employee currentUser) {
        // TODO Auto-generated method stub

    }

    private static void createEmployeeMenu(JMenuBar menuBar, Employee currentUser) {
        JMenu personalMenu = new JMenu("Работники");
        JMenuItem addEmployee = new JMenuItem("Добавить работника");
        addEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Use value change view send old userName
            }
        });

        JMenuItem fireEmployee = new JMenuItem("Уволить работника");
        fireEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Use value change view send "Старый пароль"
            }
        });

        JMenuItem showEmployee = new JMenuItem("Просмотреть работников");
        showEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Go to login view and delete all session data
            }
        });

        personalMenu.add(addEmployee);
        personalMenu.add(fireEmployee);
        personalMenu.add(showEmployee);

        menuBar.add(personalMenu);
    }
}
