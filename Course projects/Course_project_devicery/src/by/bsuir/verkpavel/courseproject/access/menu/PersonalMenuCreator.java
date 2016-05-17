package by.bsuir.verkpavel.courseproject.access.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import by.bsuir.verkpavel.courseproject.dao.DeliveryServiceDao;
import by.bsuir.verkpavel.courseproject.dao.entity.Authentication;
import by.bsuir.verkpavel.courseproject.ui.LoginView;
import by.bsuir.verkpavel.courseproject.ui.MainView;

public class PersonalMenuCreator extends BaseMenuCreator {

    private JMenu personalMenu;

    public PersonalMenuCreator(BaseMenuCreator next) {
        super(next);
    }

    @Override
    public void showMenu(JMenuBar menuBar) {
        personalMenu = new JMenu("Мой профиль");
        JMenuItem changeLogin = new JMenuItem("Изменить логин");
        changeLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newLogin = JOptionPane.showInputDialog("Введите новое имя пользователя:");
                if (newLogin!=null && !newLogin.isEmpty()) {
                    Authentication authentication = getEmployee().getAuthentication();
                    authentication.setUserName(newLogin);
                    DeliveryServiceDao.getInstance().updateRecord(authentication);
                    JOptionPane.showMessageDialog(null, "Имя пользователя успешно изменено", "Message",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        JMenuItem changePassword = new JMenuItem("Изменить пароль");
        changePassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newPassword = JOptionPane.showInputDialog("Введите новый пароль:");
                if (newPassword!=null && !newPassword.isEmpty()) {
                    Authentication authentication = getEmployee().getAuthentication();
                    authentication.setPassword(newPassword);
                    DeliveryServiceDao.getInstance().updateRecord(authentication);
                    JOptionPane.showMessageDialog(null, "Пароль успешно изменен", "Message",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        JMenuItem logOut = new JMenuItem("Выйти из учетной записи");
        logOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginView loginView = new LoginView();
                loginView.showView();
                MainView.closeForm();
            }
        });

        personalMenu.add(changeLogin);
        personalMenu.add(changePassword);
        personalMenu.add(logOut);

        menuBar.add(personalMenu);

    }

    @Override
    public void editMenu(JMenuBar menuBar) {
        // Not need implement

    }

    @Override
    public void deleteMenu(JMenuBar menuBar) {
        // Not need implement

    }

}
