package by.bsuir.verkpavel.courseproject.access.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import by.bsuir.verkpavel.courseproject.ui.LoginView;

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
