package by.bsuir.verkpavel.courseproject.ui.add;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.JXDatePicker;

import by.bsuir.verkpavel.courseproject.dao.DeliveryServiceDao;
import by.bsuir.verkpavel.courseproject.dao.Describable;
import by.bsuir.verkpavel.courseproject.dao.entity.CorporateCar;
import by.bsuir.verkpavel.courseproject.dao.entity.Delivery;
import by.bsuir.verkpavel.courseproject.dao.entity.DeliveryStatus;
import by.bsuir.verkpavel.courseproject.dao.entity.Driver;
import by.bsuir.verkpavel.courseproject.dao.entity.Office;
import by.bsuir.verkpavel.courseproject.resources.Messages;
//TODO Change UI
public class AddDeliveryView extends JFrame {
    private static final long serialVersionUID = 2883993883146596569L;

    private static Logger log = Logger.getLogger(AddDeliveryView.class);

    private JPanel mainPanel;

    private JXDatePicker startDateField;
    private JXDatePicker endDateField;

    private JComboBox<String> fromOfficesComboBox;
    private JComboBox<String> toOfficesComboBox;
    private JComboBox<String> corporateCarComboBox;
    private JComboBox<String> driversComboBox;

    private List<CorporateCar> corporateCars;
    private List<Office> offices;
    private List<Driver> drivers;

     public AddDeliveryView() {
        configureDefaultLayot();
        fillComboBoxes();
    }

    private void configureDefaultLayot() {
        initialazeLayout();
        createElements();
    }

    private void createActionElements() {
        startDateField = new JXDatePicker();
        startDateField.setDate(new Date());
        startDateField.setBounds(16, 28, 152, 38);
        mainPanel.add(startDateField);

        fromOfficesComboBox = new JComboBox<String>();
        fromOfficesComboBox.setBounds(10, 171, 126, 26);
        mainPanel.add(fromOfficesComboBox);

        corporateCarComboBox = new JComboBox<String>();
        corporateCarComboBox.setBounds(195, 40, 509, 26);
        mainPanel.add(corporateCarComboBox);

        endDateField = new JXDatePicker();
        endDateField.setBounds(16, 255, 144, 38);
        endDateField.setDate(new Date());
        mainPanel.add(endDateField);

        JButton addButton = new JButton("Добавить");
        addButton.setBounds(265, 367, 89, 23);
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Delivery delivery = getDelivery();

                boolean isSuccessfully = DeliveryServiceDao.getInstance().addRecord(delivery);
                if (isSuccessfully) {
                    JOptionPane.showMessageDialog(null, Messages.DELIVERY_SUCCESSFULLY_ADDED.get(), "Message",
                            JOptionPane.PLAIN_MESSAGE);
                    log.info(Messages.DELIVERY_SUCCESSFULLY_ADDED.get());
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, Messages.ERROR_WHILE_ADD_RECORD.get(), "Error",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        mainPanel.add(addButton);

        driversComboBox = new JComboBox<String>();
        driversComboBox.setBounds(419, 267, 126, 26);
        mainPanel.add(driversComboBox);
    }

    private void createElements() {
        createLabels();
        createActionElements();
    }

    private void createLabels() {
        JLabel startDate = new JLabel("Дата отправки");
        startDate.setBounds(16, 11, 102, 14);
        mainPanel.add(startDate);

        JLabel endDate = new JLabel("Даты прибытия");
        endDate.setBounds(16, 238, 114, 14);
        mainPanel.add(endDate);

        JLabel parselTypeLabel = new JLabel("Тип посылки");
        parselTypeLabel.setBounds(20, 153, 108, 16);
        mainPanel.add(parselTypeLabel);

        JLabel clientLabel = new JLabel("Клиент");
        clientLabel.setBounds(195, 22, 126, 16);
        mainPanel.add(clientLabel);

        JLabel paymentsTypeLabel = new JLabel("Cпособ оплаты");
        paymentsTypeLabel.setBounds(429, 249, 108, 16);
        mainPanel.add(paymentsTypeLabel);
    }

    private void fillComboBox(JComboBox<String> target, List<? extends Describable> source) {
        for (Describable item : source) {
            target.addItem(item.getDescription());
        }
    }

    private void fillComboBoxes() {
        corporateCars = DeliveryServiceDao.getInstance().getAllRecord(CorporateCar.class);
        offices = DeliveryServiceDao.getInstance().getAllRecord(Office.class);
        drivers = DeliveryServiceDao.getInstance().getAllRecord(Driver.class);

        fillComboBox(corporateCarComboBox, corporateCars);
        fillComboBox(fromOfficesComboBox, offices);
        fillComboBox(toOfficesComboBox, offices);
        fillComboBox(driversComboBox, drivers);
    }

    private Delivery getDelivery() {
        Date startDate = startDateField.getDate();
        Date endDate = endDateField.getDate();

        CorporateCar corporateCar = corporateCars.get(corporateCarComboBox.getSelectedIndex());
        Office fromOffice = offices.get(fromOfficesComboBox.getSelectedIndex());
        Office toOffice = offices.get(toOfficesComboBox.getSelectedIndex());
        Driver driver = drivers.get(driversComboBox.getSelectedIndex());

        Delivery delivery = new Delivery();
        delivery.setCorporatecar(corporateCar);
        delivery.setDriver(driver);
        delivery.setEndDate(endDate);
        delivery.setStartDate(startDate);
        delivery.setFromOffice(fromOffice);
        delivery.setToOffice(toOffice);
        
        DeliveryStatus deliveryStatus = new DeliveryStatus();
        deliveryStatus.setIdDeliveryStatus(1);
        
        delivery.setDeliverystatus(deliveryStatus);

        return delivery;
    }

    private void initialaze() {
        this.setSize(720, 400);
        this.setTitle("Добавление доставки");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        this.setVisible(true);
    }

    private void initialazeLayout() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        mainPanel = new JPanel();
        setContentPane(mainPanel);
        mainPanel.setLayout(null);
    }

    public void showView() {
        initialaze();
    }
}
