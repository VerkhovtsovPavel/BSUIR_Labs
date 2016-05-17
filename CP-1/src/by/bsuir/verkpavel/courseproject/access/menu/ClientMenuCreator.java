package by.bsuir.verkpavel.courseproject.access.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import by.bsuir.verkpavel.courseproject.dao.DeliveryServiceDao;
import by.bsuir.verkpavel.courseproject.dao.entity.Client;
import by.bsuir.verkpavel.courseproject.ui.EntityShowView;
import by.bsuir.verkpavel.courseproject.ui.add.AddClientView;

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
                List<Client> clients = DeliveryServiceDao.getInstance().getAllRecord(Client.class);
                EntityShowView entityShowView = new EntityShowView(clients);
                entityShowView.showView();
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
                AddClientView addClientView = new AddClientView();
                addClientView.showView();
            }
        });
        personalMenu.add(addClient);

    }

    @Override
    public void deleteMenu(JMenuBar menuBar) {
        JMenuItem deleteClient = new JMenuItem("Удалить клиента");
        deleteClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Client> clients = DeliveryServiceDao.getInstance().getAllRecord(Client.class);
                EntityShowView entityShowView = new EntityShowView(clients, true);
                entityShowView.showView();
            }
        });
        personalMenu.add(deleteClient);
    }
}
