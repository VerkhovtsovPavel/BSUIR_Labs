package ui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class PersonalMenuCreator extends BaseMenuCreator {

    private JMenu personalMenu;

    public PersonalMenuCreator(BaseMenuCreator next) {
        super(next);
    }

    @Override
    public JMenu createMenuTab() {
        personalMenu = new JMenu("Мой профиль");
        JMenuItem changeLogin = new JMenuItem("Изменить логин");
        changeLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

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

            }
        });

        personalMenu.add(changeLogin);
        personalMenu.add(changePassword);
        personalMenu.add(logOut);

        return personalMenu;
    }
}
