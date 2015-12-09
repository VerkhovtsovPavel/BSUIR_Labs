package by.bsuir.verkpavel.courseproject.access.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import by.bsuir.verkpavel.courseproject.dao.DeliveryServiceDao;
import by.bsuir.verkpavel.courseproject.dao.entity.Authentication;
import by.bsuir.verkpavel.courseproject.ui.LoginView;
import by.bsuir.verkpavel.courseproject.ui.change.ValueChangeView;

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
                Authentication authentication = getEmployee().getAuthentication();
                ValueChangeView valueChangeView = new ValueChangeView(authentication.getUserName());
                valueChangeView.showView();
                //TODO Don't work
                waitNewValue(valueChangeView);
                String newLogin = valueChangeView.getNewValue();
                if (!newLogin.isEmpty()) {
                    authentication.setUserName(newLogin);
                    DeliveryServiceDao.getInstance().updateRecord(authentication);
                }
            }
        });

        JMenuItem changePassword = new JMenuItem("Изменить пароль");
        changePassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ValueChangeView valueChangeView = new ValueChangeView("Старый пароль");
                valueChangeView.showView();
                waitNewValue(valueChangeView );
                String newPassword = valueChangeView.getNewValue();
                if (!newPassword.isEmpty()) {
                    Authentication authentication = getEmployee().getAuthentication();
                    authentication.setPassword(newPassword);
                    DeliveryServiceDao.getInstance().updateRecord(authentication);
                }
            }
        });

        JMenuItem logOut = new JMenuItem("Выйти из учетной записи");
        logOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginView loginView = new LoginView();
                loginView.showView();
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
