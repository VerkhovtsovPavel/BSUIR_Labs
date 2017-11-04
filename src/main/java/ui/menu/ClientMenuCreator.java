package ui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class ClientMenuCreator extends BaseMenuCreator {

    private JMenu personalMenu;

    public ClientMenuCreator(BaseMenuCreator next) {
        super(next);
        personalMenu = new JMenu("Клиенты");
    }

    @Override
    public JMenu createMenuTab() {
        JMenuItem showClient = new JMenuItem("Просмотреть клиентов");
        showClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        personalMenu.add(showClient);
        return personalMenu;
    }
}
