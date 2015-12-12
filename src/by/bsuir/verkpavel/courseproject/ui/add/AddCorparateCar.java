package by.bsuir.verkpavel.courseproject.ui.add;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.JXDatePicker;

import by.bsuir.verkpavel.courseproject.dao.DeliveryServiceDao;
import by.bsuir.verkpavel.courseproject.dao.Describable;
import by.bsuir.verkpavel.courseproject.dao.entity.CorporateCar;
import by.bsuir.verkpavel.courseproject.dao.entity.DriverLicenceCategory;
import by.bsuir.verkpavel.courseproject.dao.entity.Office;
import by.bsuir.verkpavel.courseproject.dao.entity.Rate;
import by.bsuir.verkpavel.courseproject.resources.Messages;
import by.bsuir.verkpavel.courseproject.resources.ProjectProperties;
public class AddCorparateCar extends JFrame {
    private static final long serialVersionUID = 2883993883146596569L;

    private static Logger log = Logger.getLogger(AddCorparateCar.class);

    private JPanel mainPanel;

    private JXDatePicker buyDateField;
    private JFormattedTextField carNumber;
    private JTextField carMark;
    private JSpinner widthSpinner;
    private JSpinner weigthSpinner;
    private JSpinner heightSpinner;
    private JSpinner depthSpinner;
    private JComboBox<String> driverLicenceCategoryComboBox;
    private JComboBox<String> officeComboBox;

    private List<DriverLicenceCategory> driverLicenceCategorys;
    private List<Office> offices;

    private Rate lastRate;

    public AddCorparateCar() {
        loadRates();
        configureDefaultLayot();
        fillComboBoxes();
    }

    private void configureDefaultLayot() {
        initialazeLayout();
        createElements();
    }

    private void createActionElements() {
        buyDateField = new JXDatePicker();
        buyDateField.setDate(new Date());
        buyDateField.setBounds(16, 28, 152, 40);
        mainPanel.add(buyDateField);

        carNumber = new JFormattedTextField(ProjectProperties.getCarNumberMask());
        carNumber.setBounds(327, 35, 114, 26);
        carNumber.setColumns(10);
        mainPanel.add(carNumber);
        
        carMark = new JTextField();
        carMark.setBounds(183, 37, 114, 26);
        carMark.setColumns(10);
        mainPanel.add(carMark);
        
        JButton addButton = new JButton("Добавить");
        addButton.setBounds(260, 302, 89, 23);
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               CorporateCar parcel = getCorporateCar();

                boolean isSuccessfully = DeliveryServiceDao.getInstance().addRecord(parcel);
                if (isSuccessfully) {
                    JOptionPane.showMessageDialog(null, Messages.CORPORATE_CAR_SUCCESSFULLY_ADDED.get(), "Message",
                            JOptionPane.PLAIN_MESSAGE);
                    log.info(Messages.CORPORATE_CAR_SUCCESSFULLY_ADDED.get());
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, Messages.ERROR_WHILE_ADD_RECORD.get(), "Error",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        mainPanel.add(addButton);

        driverLicenceCategoryComboBox = new JComboBox<String>();
        driverLicenceCategoryComboBox.setBounds(463, 38, 126, 26);
        mainPanel.add(driverLicenceCategoryComboBox);

        heightSpinner = new JSpinner();
        heightSpinner.setModel(new SpinnerNumberModel(1, 1, 3000, 1));
        heightSpinner.setBounds(60, 111, 58, 40);
        heightSpinner.addChangeListener(new CostChange());
        mainPanel.add(heightSpinner);

        depthSpinner = new JSpinner();
        depthSpinner.setModel(new SpinnerNumberModel(1, 1, 3000, 1));
        depthSpinner.setBounds(379, 114, 58, 40);
        depthSpinner.addChangeListener(new CostChange());
        mainPanel.add(depthSpinner);

        weigthSpinner = new JSpinner();
        weigthSpinner.setModel(new SpinnerNumberModel(1, 1, 3000, 1));
        weigthSpinner.setBounds(228, 114, 58, 40);
        weigthSpinner.addChangeListener(new CostChange());
        mainPanel.add(weigthSpinner);

        widthSpinner = new JSpinner();
        widthSpinner.setModel(new SpinnerNumberModel(1, 1, 250000, 1));
        widthSpinner.setBounds(513, 108, 102, 40);
        widthSpinner.addChangeListener(new CostChange());
        mainPanel.add(widthSpinner);
        
        officeComboBox = new JComboBox<String>();
        officeComboBox.setBounds(16, 226, 638, 26);
        mainPanel.add(officeComboBox);
        
        JLabel label = new JLabel("Офис");
        label.setBounds(22, 202, 46, 14);
        mainPanel.add(label);
    }

    private void createElements() {
        createLabels();
        createActionElements();
    }

    private void createLabels() {
        JLabel acceptanceDate = new JLabel("Дата покупки");
        acceptanceDate.setBounds(16, 11, 102, 14);
        mainPanel.add(acceptanceDate);

        JLabel parselTypeLabel = new JLabel("Марка");
        parselTypeLabel.setBounds(183, 10, 86, 16);
        mainPanel.add(parselTypeLabel);

        JLabel sumLabel = new JLabel("Номер");
        sumLabel.setBounds(327, 10, 98, 16);
        mainPanel.add(sumLabel);

        JLabel heigthLb = new JLabel("Высота");
        heigthLb.setBounds(16, 114, 44, 14);
        mainPanel.add(heigthLb);

        JLabel widthLabel = new JLabel("Вес");
        widthLabel.setBounds(481, 111, 22, 14);
        mainPanel.add(widthLabel);
        
        JLabel officeLabel = new JLabel("Офис");
        officeLabel.setBounds(299, 171, 22, 14);

        JLabel smLabel = new JLabel("см.");
        smLabel.setBounds(128, 114, 22, 14);
        mainPanel.add(smLabel);

        JLabel label = new JLabel("см.");
        label.setBounds(290, 117, 22, 14);
        mainPanel.add(label);

        JLabel label_1 = new JLabel("см.");
        label_1.setBounds(449, 117, 22, 14);
        mainPanel.add(label_1);

        JLabel gramm = new JLabel("грамм");
        gramm.setBounds(625, 114, 58, 14);
        mainPanel.add(gramm);

        JLabel paymentsTypeLabel = new JLabel("Категория прав");
        paymentsTypeLabel.setBounds(463, 11, 108, 16);
        mainPanel.add(paymentsTypeLabel);

        JLabel weigthLabel = new JLabel("Ширина");
        weigthLabel.setBounds(167, 114, 64, 14);
        mainPanel.add(weigthLabel);

        JLabel depthLabel = new JLabel("Глубина");
        depthLabel.setBounds(322, 114, 64, 14);
        mainPanel.add(depthLabel);
    }

    private void fillComboBox(JComboBox<String> target, List<? extends Describable> source) {
        for (Describable item : source) {
            target.addItem(item.getDescription());
        }
    }

    private void fillComboBoxes() {

        driverLicenceCategorys = DeliveryServiceDao.getInstance().getAllRecord(DriverLicenceCategory.class);
        offices = DeliveryServiceDao.getInstance().getAllRecord(Office.class);
        
        fillComboBox(driverLicenceCategoryComboBox, driverLicenceCategorys);
        fillComboBox(officeComboBox, offices);
    }

    private CorporateCar getCorporateCar() {
        Date buyDate = buyDateField.getDate();
        Office office = offices.get(officeComboBox.getSelectedIndex());
        DriverLicenceCategory driverLicenceCategory = driverLicenceCategorys.get(driverLicenceCategoryComboBox.getSelectedIndex());

        String carNumberValue = carNumber.getText();
        String mark = carMark.getText();

        int height = (int) heightSpinner.getValue();
        int weigth = (int) weigthSpinner.getValue();
        int depth = (int) depthSpinner.getValue();
        int width = (int) widthSpinner.getValue();

        CorporateCar corporateCar = new CorporateCar();
        corporateCar.setDriverlicencecategory(driverLicenceCategory);
        corporateCar.setBuyDate(buyDate);
        corporateCar.setNumber(carNumberValue);


        corporateCar.setMaxDepth(depth);
        corporateCar.setMaxHeight(height);
        corporateCar.setMaxWeigth(weigth);
        corporateCar.setMaxWidth(width);
        corporateCar.setMark(mark);
        corporateCar.setOffice(office);
        return corporateCar;
    }

    private void initialaze() {
        this.setSize(700, 380);
        this.setTitle("Добавление посылки");
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

    private void loadRates() {
        List<Rate> rateList = DeliveryServiceDao.getInstance().getAllRecord(Rate.class);
        if (rateList.isEmpty()) {
            lastRate = ProjectProperties.getDefaultRate();
            log.warn("Using default rates");
        } else {
            lastRate = rateList.get(rateList.size() - 1);
        }
    }

    public void showView() {
        initialaze();
    }

    private class CostChange implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            int height = (int) heightSpinner.getValue();
            int weigth = (int) weigthSpinner.getValue();
            int depth = (int) depthSpinner.getValue();
            int width = (int) widthSpinner.getValue();

            int cost = height * lastRate.getHeigth() + weigth * lastRate.getWeigth() + depth * lastRate.getDepth()
                    + width * lastRate.getWidth();

            carNumber.setValue(cost);
        }
    }
}
