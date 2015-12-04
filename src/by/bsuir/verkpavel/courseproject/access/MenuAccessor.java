package by.bsuir.verkpavel.courseproject.access;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import by.bsuir.verkpavel.courseproject.access.menu.BaseMenuCreator;
import by.bsuir.verkpavel.courseproject.access.menu.ClientMenuCreator;
import by.bsuir.verkpavel.courseproject.access.menu.PersonalMenuCreator;
import by.bsuir.verkpavel.courseproject.dao.entity.Employee;

public class MenuAccessor {

    public static JMenuBar createMenu(Employee currentUser) {
        JMenuBar menuBar = new JMenuBar();
//        createPersonalMenu(menuBar, currentUser);
//        createEmployeeMenu(menuBar, currentUser);
//        createClientMenu(menuBar, currentUser);
//        createDeliveryMenu(menuBar, currentUser);
//        createParcelMenu(menuBar, currentUser);
//        createCorporateCarMenu(menuBar, currentUser);
        
        BaseMenuCreator menuCreator = new PersonalMenuCreator(new ClientMenuCreator(null));
        menuCreator.process(currentUser, menuBar);
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
                // TODO Use value change view send old password
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
        switch (currentUser.getPermission().getIdPermissions()) {
        case 3:
            JMenuItem cancelСar = new JMenuItem("Списать машины");
            cancelСar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // TODO
                }
            });
            personalMenu.add(cancelСar);
        case 2:
            JMenuItem addCar = new JMenuItem("Добавить машины");
            addCar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // TODO
                }
            });
            personalMenu.add(addCar);
        case 1:
            JMenuItem showCar = new JMenuItem("Просмотреть работников");
            showCar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // TODO
                }
            });
            personalMenu.add(showCar);
        }

        menuBar.add(personalMenu);
    }

    private static void createParcelMenu(JMenuBar menuBar, Employee currentUser) {
        JMenu personalMenu = new JMenu("Посылки");
        switch (currentUser.getPermission().getIdPermissions()) {
        case 3:
            JMenuItem deleteParcel = new JMenuItem("Удалить посылку");
            deleteParcel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // TODO
                }
            });
            personalMenu.add(deleteParcel);

        case 2:
            JMenuItem addParcel = new JMenuItem("Добавить посылку");
            addParcel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // TODO
                }
            });
            personalMenu.add(addParcel);

        case 1:
            JMenuItem showParcel = new JMenuItem("Просмотреть посылки");
            showParcel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // TODO
                }
            });
            personalMenu.add(showParcel);
        }

        menuBar.add(personalMenu);
    }

    private static void createDeliveryMenu(JMenuBar menuBar, Employee currentUser) {
        JMenu personalMenu = new JMenu("Доставки");
        switch (currentUser.getPermission().getIdPermissions()) {
        case 3:
            JMenuItem deleteParcelToDelivery = new JMenuItem("Удалить товар в достаку");
            deleteParcelToDelivery.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // TODO
                }
            });

            personalMenu.add(deleteParcelToDelivery);
        case 2:
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
            personalMenu.add(addDelivery);
            personalMenu.add(addParcelToDelivery);
        case 1:

            JMenuItem showDelivery = new JMenuItem("Просмотреть доставки");
            showDelivery.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // TODO
                }
            });
            personalMenu.add(showDelivery);
        }

        menuBar.add(personalMenu);
    }

    private static void createClientMenu(JMenuBar menuBar, Employee currentUser) {
        JMenu personalMenu = new JMenu("Клиенты");
        switch (currentUser.getPermission().getIdPermissions()) {
        case 3:
            JMenuItem deleteClient = new JMenuItem("Удалить клиента");
            deleteClient.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // TODO Use value change view send "Старый пароль"
                }
            });
            personalMenu.add(deleteClient);

        case 2:
            JMenuItem addClient = new JMenuItem("Добавить клиента");
            addClient.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // TODO Use value change view send old userName
                }
            });
            personalMenu.add(addClient);

        case 1:
            JMenuItem showClient = new JMenuItem("Просмотреть клиентов");
            showClient.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // TODO Go to login view and delete all session data
                }
            });
            personalMenu.add(showClient);
        }

        menuBar.add(personalMenu);
    }

    private static void createEmployeeMenu(JMenuBar menuBar, Employee currentUser) {
        JMenu personalMenu = new JMenu("Работники");
        switch (currentUser.getPermission().getIdPermissions()) {
        case 3:
            JMenuItem fireEmployee = new JMenuItem("Уволить работника");
            fireEmployee.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // TODO Use value change view send "Старый пароль"
                }
            });
            personalMenu.add(fireEmployee);
        case 2:
            JMenuItem addEmployee = new JMenuItem("Добавить работника");
            addEmployee.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // TODO Use value change view send old userName
                }
            });

            personalMenu.add(addEmployee);

        case 1:
            JMenuItem showEmployee = new JMenuItem("Просмотреть работников");
            showEmployee.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // TODO Go to login view and delete all session data
                }
            });
            personalMenu.add(showEmployee);
        }

        menuBar.add(personalMenu);
    }
}
