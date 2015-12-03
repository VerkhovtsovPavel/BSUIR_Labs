package by.bsuir.verkpavel.courseproject.ui.add;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.JXDatePicker;

import by.bsuir.verkpavel.courseproject.dao.DeliveryServiceDao;
import by.bsuir.verkpavel.courseproject.dao.entity.Client;
import by.bsuir.verkpavel.courseproject.resources.Messages;

import com.j256.ormlite.dao.Dao;

public class AddClientView extends JFrame {
    private static final long serialVersionUID = 2883993883146596569L;
    private static Logger log = Logger.getLogger(AddClientView.class);

    private JPanel mainPanel;
    private JTextField userNameTextField;
    private JXDatePicker datePicker;

    public AddClientView() {
        configureDefaultLayot();
    }

    public void showView() {
        this.setSize(740, 160);
        this.setTitle("Добавление клиента");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        this.setVisible(true);
    }

    private void createLabels() {
        JLabel addDateLabel = new JLabel("Дата добавления");
        addDateLabel.setBounds(23, 79, 121, 14);
        mainPanel.add(addDateLabel);

        JLabel userNameLabel = new JLabel("Имя пользователя");
        userNameLabel.setBounds(23, 25, 121, 14);
        mainPanel.add(userNameLabel);
    }

    private void configureDefaultLayot() {
        initialazeLayout();
        createElements();
    }

    private void initialazeLayout() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        mainPanel = new JPanel();
        setContentPane(mainPanel);
        mainPanel.setLayout(null);
    }

    private void createElements() {
        createLabels();
        createActionElements();
    }

    private Client getClient() {
        String userName = userNameTextField.getText();
        Date addDate = datePicker.getDate();

        Client client = new Client();
        client.setFullName(userName);
        client.setAddDate(addDate);
        return client;
    }

    private void createActionElements() {
        userNameTextField = new JTextField();
        userNameTextField.setBounds(154, 14, 562, 38);
        mainPanel.add(userNameTextField);
        userNameTextField.setColumns(10);
         
        datePicker = new JXDatePicker();
        datePicker.setBounds(156, 67, 174, 38);
        mainPanel.add(datePicker);

        JButton addButton = new JButton("Добавить");
        addButton.setBounds(342, 64, 374, 41);
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Client client = getClient();
                Dao<Client, Integer> clientDao = DeliveryServiceDao.getInstance().getDaoByClass(Client.class);
                try {
                    clientDao.create(client);
                    JOptionPane.showMessageDialog(null, Messages.CLIENT_SUCCESSFULLY_ADDED.get(), "Message",
                            JOptionPane.PLAIN_MESSAGE);
                    log.info(Messages.CLIENT_SUCCESSFULLY_ADDED.get());
                    dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, Messages.ERROR_WHILE_ADD_CLIENT.get(), "Error",
                            JOptionPane.PLAIN_MESSAGE);
                    log.error(Messages.ERROR_WHILE_ADD_CLIENT.get(), ex);
                }
            }
        });
        mainPanel.add(addButton);
    }
}
