package by.bsuir.verkpavel.adb.ui.client;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import by.bsuir.verkpavel.adb.data.DataProvider;
import by.bsuir.verkpavel.adb.data.entity.Client;
import by.bsuir.verkpavel.adb.ui.ActionMode;
import by.bsuir.verkpavel.adb.ui.MainView;

public class ShowUsersView extends JFrame {
    private static final long serialVersionUID = 2883993883146596569L;
    private JPanel mainPanel;

    ArrayList<Client> clients;

    private static void initialaze() {
        ShowUsersView frame = new ShowUsersView();
        frame.setSize(460, 310);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setVisible(true);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                MainView.create();
            }
        });
    }

    public static void create() {
        initialaze();
    }

    private ShowUsersView() {
        configureDefaultLayot();
    }

    private void configureDefaultLayot() {
        setResizable(false);
        setTitle("\u0414\u043E\u0431\u0430\u0432\u043B\u0435\u043D\u0438\u0435 \u043A\u043B\u0438\u0435\u043D\u0442\u0430");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        mainPanel = new JPanel();
        setContentPane(mainPanel);
        mainPanel.setLayout(null);

        clients = DataProvider.getInstance().getAllClients();

        final DefaultListModel<String> listModel = new DefaultListModel<>();
        final JList<String> list = new JList<String>(listModel);
        list.setBounds(10, 32, 422, 176);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (list.getSelectedIndex() != -1) {
                        ActionView.create(ActionMode.SHOW, clients.get(list.getSelectedIndex()));
                        dispose();
                    }
                }
            }
        });
        mainPanel.add(list);

        JButton editBtn = new JButton(
                "\u0420\u0435\u0434\u0430\u043A\u0442\u0438\u0440\u043E\u0432\u0430\u0442\u044C");
        editBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (list.getSelectedIndex() != -1) {
                    ActionView.create(ActionMode.EDIT, clients.get(list.getSelectedIndex()));
                    dispose();
                }
            }

        });

        editBtn.setBounds(10, 238, 152, 23);
        mainPanel.add(editBtn);

        JLabel usersLbl = new JLabel("\u041A\u043B\u0438\u0435\u043D\u0442\u044B");
        usersLbl.setBounds(190, 6, 66, 14);
        mainPanel.add(usersLbl);

        JButton deleteBtn = new JButton("\u0423\u0434\u0430\u043B\u0438\u0442\u044C");
        deleteBtn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (list.getSelectedIndex() != -1) {
                    int selection = list.getSelectedIndex();
                    DataProvider.getInstance().deleteClient(clients.get(selection));
                    listModel.remove(selection);
                    clients.remove(selection);
                }
            }
        }

        );
        deleteBtn.setBounds(321, 238, 111, 23);
        mainPanel.add(deleteBtn);

        JButton addButton = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
        addButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                ActionView.create(ActionMode.ADD, null);
            }

        });
        addButton.setBounds(174, 238, 111, 23);
        mainPanel.add(addButton);

        listModel.clear();
        for (Client client : clients) {
            listModel
                    .addElement(client.firstName + " " + client.lastName + " " + client.middleName);
        }
    }
}
