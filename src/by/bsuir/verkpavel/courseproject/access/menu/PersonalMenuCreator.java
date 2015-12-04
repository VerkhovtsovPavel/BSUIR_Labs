package by.bsuir.verkpavel.courseproject.access.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class PersonalMenuCreator extends BaseMenuCreator {

    private JMenu personalMenu;

    public PersonalMenuCreator(BaseMenuCreator next) {
        super(next);
        personalMenu = new JMenu("Мой профиль");
    }

    @Override
    public void showMenu(JMenuBar menuBar) {
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

    @Override
    public void editMenu(JMenuBar menuBar) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteMenu(JMenuBar menuBar) {
        // TODO Auto-generated method stub

    }

}
