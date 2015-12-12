package by.bsuir.verkpavel.courseproject.ui.add;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private Office office;

    protected int lastOfficeIndex;

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
        startDateField.setBounds(22, 255, 152, 40);
        startDateField.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if(startDateField.getDate().after(endDateField.getDate())){
                    endDateField.setDate(startDateField.getDate());
                } 
            }
        });
        mainPanel.add(startDateField);
        
        fromOfficesComboBox = new JComboBox<String>();
        fromOfficesComboBox.setBounds(16, 104, 764, 26);
        mainPanel.add(fromOfficesComboBox);

        toOfficesComboBox = new JComboBox<String>();
        toOfficesComboBox.setBounds(16, 184, 764, 26);
        mainPanel.add(toOfficesComboBox);

        corporateCarComboBox = new JComboBox<String>();
        corporateCarComboBox.setBounds(16, 40, 764, 26);
        mainPanel.add(corporateCarComboBox);

        endDateField = new JXDatePicker();
        endDateField.setBounds(209, 255, 144, 40);
        endDateField.setDate(new Date());
        endDateField.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if(startDateField.getDate().after(endDateField.getDate())){
                    endDateField.setDate(startDateField.getDate());
                }  
            }
        });
        mainPanel.add(endDateField);

        JButton addButton = new JButton("Добавить");
        addButton.setBounds(315, 307, 139, 40);
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
                    JOptionPane.showMessageDialog(null, "Водитель имеет недопустимую категорию прав", "Error",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        mainPanel.add(addButton);

        driversComboBox = new JComboBox<String>();
        driversComboBox.setBounds(419, 267, 361, 26);
        mainPanel.add(driversComboBox);
    }

    private void createElements() {
        createLabels();
        createActionElements();
    }

    private void createLabels() {
        JLabel startDate = new JLabel("Дата отправки");
        startDate.setBounds(22, 238, 102, 14);
        mainPanel.add(startDate);

        JLabel endDate = new JLabel("Даты прибытия");
        endDate.setBounds(209, 238, 114, 14);
        mainPanel.add(endDate);
        
        JLabel toOfficeLabel = new JLabel("В");
        toOfficeLabel.setBounds(22, 151, 108, 16);
        mainPanel.add(toOfficeLabel);

        JLabel fromOfficeLabel = new JLabel("Из");
        fromOfficeLabel.setBounds(22, 77, 108, 16);
        mainPanel.add(fromOfficeLabel);

        JLabel corporateCarLabel = new JLabel("Машина");
        corporateCarLabel.setBounds(16, 13, 126, 16);
        mainPanel.add(corporateCarLabel);

        JLabel driverLabel = new JLabel("Водитель");
        driverLabel.setBounds(429, 249, 108, 16);
        mainPanel.add(driverLabel);
    }

    private void fillComboBox(JComboBox<String> target, List<? extends Describable> source) {
        for (Describable item : source) {
            target.addItem(item.getDescription());
        }
    }

    private void fillComboBoxes() {
        corporateCars = DeliveryServiceDao.getInstance().getActiveRecords(CorporateCar.class);
        offices = DeliveryServiceDao.getInstance().getAllRecord(Office.class);
        drivers = DeliveryServiceDao.getInstance().getActiveRecords(Driver.class);

        fillComboBox(corporateCarComboBox, corporateCars);
        fillComboBox(fromOfficesComboBox, offices);

        fromOfficesComboBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (office != null) {
                    offices.add(lastOfficeIndex, office);
                }
                lastOfficeIndex = fromOfficesComboBox.getSelectedIndex();
                office = offices.get(fromOfficesComboBox.getSelectedIndex());
                offices.remove(office);
                toOfficesComboBox.removeAllItems();
                fillComboBox(toOfficesComboBox, offices);
            }
        });
        
        fromOfficesComboBox.setSelectedIndex(1);
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
        this.setSize(800, 400);
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
