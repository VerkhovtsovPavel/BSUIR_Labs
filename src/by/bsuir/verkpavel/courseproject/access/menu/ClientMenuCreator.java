package by.bsuir.verkpavel.courseproject.access.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ClientMenuCreator extends BaseMenuCreator {

    private JMenu personalMenu;

    public ClientMenuCreator(BaseMenuCreator next) {
        super(next);
        personalMenu = new JMenu("Клиенты");
    }

    @Override
    public void showMenu(JMenuBar menuBar) {
        JMenuItem showClient = new JMenuItem("Просмотреть клиентов");
        showClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Go to login view and delete all session data
            }
        });
        personalMenu.add(showClient);
        menuBar.add(personalMenu);
    }

    @Override
    public void editMenu(JMenuBar menuBar) {
        JMenuItem addClient = new JMenuItem("Добавить клиента");
        addClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Use value change view send old userName
            }
        });
        personalMenu.add(addClient);

    }

    @Override
    public void deleteMenu(JMenuBar menuBar) {
        JMenuItem deleteClient = new JMenuItem("Удалить клиента");
        deleteClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Use value change view send "Старый пароль"
            }
        });
        personalMenu.add(deleteClient);
    }
}
