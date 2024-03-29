package by.bsuir.verkpavel.courseproject.ui.add;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.JXDatePicker;

import by.bsuir.verkpavel.courseproject.dao.DeliveryServiceDao;
import by.bsuir.verkpavel.courseproject.dao.Describable;
import by.bsuir.verkpavel.courseproject.dao.entity.Client;
import by.bsuir.verkpavel.courseproject.dao.entity.MarkParcel;
import by.bsuir.verkpavel.courseproject.dao.entity.Office;
import by.bsuir.verkpavel.courseproject.dao.entity.Parcel;
import by.bsuir.verkpavel.courseproject.dao.entity.Payment;
import by.bsuir.verkpavel.courseproject.dao.entity.PaymentsSystemType;
import by.bsuir.verkpavel.courseproject.dao.entity.Rate;
import by.bsuir.verkpavel.courseproject.resources.Messages;
import by.bsuir.verkpavel.courseproject.resources.ProjectProperties;

//TODO Fix UI
public class AddParcelView extends JFrame {
    private static final long serialVersionUID = 2883993883146596569L;

    private static Logger log = Logger.getLogger(AddParcelView.class);

    private JPanel mainPanel;

    private JXDatePicker acceptanceDateField;
    private JXDatePicker payDateField;
    private JFormattedTextField sumField;
    private JComboBox<String> markParcelComboBox;
    private JComboBox<String> clientComboBox;
    private JComboBox<String> paymentsSystemTypeComboBox;
    private JSpinner widthSpinner;
    private JSpinner weigthSpinner;
    private JSpinner heightSpinner;
    private JSpinner depthSpinner;
    private JComboBox<String> officeComboBox;

    private List<Client> clients;
    private List<MarkParcel> markParcels;
    private List<PaymentsSystemType> paymentSystemTypes;
    private List<Office> offices;

    private Rate lastRate;

    public AddParcelView() {
        loadRates();
        configureDefaultLayot();
        fillComboBoxes();
    }

    private void configureDefaultLayot() {
        initialazeLayout();
        createElements();
    }

    private void createActionElements() {
        acceptanceDateField = new JXDatePicker();
        acceptanceDateField.setDate(new Date());
        acceptanceDateField.setBounds(16, 28, 152, 40);
        mainPanel.add(acceptanceDateField);

        markParcelComboBox = new JComboBox<String>();
        markParcelComboBox.setBounds(16, 268, 226, 26);
        markParcelComboBox.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                int height = (int) heightSpinner.getValue();
                int weigth = (int) weigthSpinner.getValue();
                int depth = (int) depthSpinner.getValue();
                int width = (int) widthSpinner.getValue();

                MarkParcel markParcel = markParcels.get(markParcelComboBox.getSelectedIndex());

                int cost = height * lastRate.getHeigth() + weigth * lastRate.getWeigth() + depth * lastRate.getDepth()
                        + width * lastRate.getWidth();

                sumField.setValue(cost * markParcel.getRate());
            }
        });
        mainPanel.add(markParcelComboBox);

        clientComboBox = new JComboBox<String>();
        clientComboBox.setBounds(195, 40, 509, 26);
        mainPanel.add(clientComboBox);

        payDateField = new JXDatePicker();
        payDateField.setBounds(16, 323, 144, 40);
        payDateField.setDate(new Date());
        mainPanel.add(payDateField);

        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("be-BY"));
        format.setMaximumFractionDigits(0);
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setMinimum(0.0);
        formatter.setMaximum(10000000.0);
        formatter.setAllowsInvalid(false);
        formatter.setOverwriteMode(true);
        sumField = new JFormattedTextField(formatter);
        sumField.setBounds(237, 329, 114, 26);
        mainPanel.add(sumField);
        sumField.setColumns(10);
        sumField.setValue(0.0);
        sumField.setEnabled(false);
        sumField.setValue(lastRate.getHeigth() + lastRate.getWeigth() + lastRate.getDepth() + lastRate.getWidth());

        JButton addButton = new JButton("Добавить");
        addButton.setBounds(286, 383, 181, 23);
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Parcel parcel = getParcel();

                boolean isSuccessfully = DeliveryServiceDao.getInstance().addRecord(parcel);
                if (isSuccessfully) {
                    JOptionPane.showMessageDialog(null, Messages.PARCEL_SUCCESSFULLY_ADDED.get(), "Message",
                            JOptionPane.PLAIN_MESSAGE);
                    log.info(Messages.PARCEL_SUCCESSFULLY_ADDED.get());
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, Messages.ERROR_WHILE_ADD_RECORD.get(), "Error",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        mainPanel.add(addButton);

        paymentsSystemTypeComboBox = new JComboBox<String>();
        paymentsSystemTypeComboBox.setBounds(419, 335, 296, 26);
        mainPanel.add(paymentsSystemTypeComboBox);

        heightSpinner = new JSpinner();
        heightSpinner.setModel(new SpinnerNumberModel(1, 1, 3000, 1));
        heightSpinner.setBounds(111, 115, 80, 40);
        heightSpinner.addChangeListener(new CostChange());
        mainPanel.add(heightSpinner);

        depthSpinner = new JSpinner();
        depthSpinner.setModel(new SpinnerNumberModel(1, 1, 3000, 1));
        depthSpinner.setBounds(556, 115, 80, 40);
        depthSpinner.addChangeListener(new CostChange());
        mainPanel.add(depthSpinner);

        weigthSpinner = new JSpinner();
        weigthSpinner.setModel(new SpinnerNumberModel(1, 1, 3000, 1));
        weigthSpinner.setBounds(335, 115, 80, 40);
        weigthSpinner.addChangeListener(new CostChange());
        mainPanel.add(weigthSpinner);

        widthSpinner = new JSpinner();
        widthSpinner.setModel(new SpinnerNumberModel(1, 1, 250000, 1));
        widthSpinner.setBounds(59, 172, 102, 40);
        widthSpinner.addChangeListener(new CostChange());
        mainPanel.add(widthSpinner);

        officeComboBox = new JComboBox<String>();
        officeComboBox.setBounds(275, 268, 441, 26);
        mainPanel.add(officeComboBox);
        
        JLabel label = new JLabel("Пункт назначения");
        label.setBounds(275, 240, 183, 16);
        mainPanel.add(label);
    }

    private void createElements() {
        createLabels();
        createActionElements();
    }

    private void createLabels() {
        JLabel acceptanceDate = new JLabel("Дата приема");
        acceptanceDate.setBounds(16, 11, 102, 14);
        mainPanel.add(acceptanceDate);

        JLabel payDate = new JLabel("Даты оплаты");
        payDate.setBounds(16, 306, 114, 14);
        mainPanel.add(payDate);

        JLabel parselTypeLabel = new JLabel("Тип посылки");
        parselTypeLabel.setBounds(16, 240, 108, 16);
        mainPanel.add(parselTypeLabel);

        JLabel clientLabel = new JLabel("Клиент");
        clientLabel.setBounds(195, 22, 126, 16);
        mainPanel.add(clientLabel);

        JLabel sumLabel = new JLabel("Сумма");
        sumLabel.setBounds(183, 334, 56, 16);
        mainPanel.add(sumLabel);

        JLabel heigthLb = new JLabel("Высота");
        heigthLb.setBounds(30, 127, 63, 14);
        mainPanel.add(heigthLb);

        JLabel widthLabel = new JLabel("Вес");
        widthLabel.setBounds(16, 188, 55, 14);
        mainPanel.add(widthLabel);

        JLabel officeLabel = new JLabel("Офис");
        officeLabel.setBounds(299, 171, 22, 14);

        JLabel smLabel = new JLabel("см.");
        smLabel.setBounds(199, 127, 30, 14);
        mainPanel.add(smLabel);

        JLabel label = new JLabel("см.");
        label.setBounds(429, 127, 22, 14);
        mainPanel.add(label);

        JLabel label_1 = new JLabel("см.");
        label_1.setBounds(649, 127, 41, 14);
        mainPanel.add(label_1);

        JLabel gramm = new JLabel("грамм");
        gramm.setBounds(171, 178, 58, 14);
        mainPanel.add(gramm);

        JLabel paymentsTypeLabel = new JLabel("Cпособ оплаты");
        paymentsTypeLabel.setBounds(429, 317, 108, 16);
        mainPanel.add(paymentsTypeLabel);

        JLabel weigthLabel = new JLabel("Ширина");
        weigthLabel.setBounds(250, 127, 67, 14);
        mainPanel.add(weigthLabel);

        JLabel depthLabel = new JLabel("Длина");
        depthLabel.setBounds(479, 127, 71, 14);
        mainPanel.add(depthLabel);
    }

    private void fillComboBox(JComboBox<String> target, List<? extends Describable> source) {
        for (Describable item : source) {
            target.addItem(item.getDescription());
        }
    }

    private void fillComboBoxes() {
        clients = DeliveryServiceDao.getInstance().getAllRecord(Client.class);
        markParcels = DeliveryServiceDao.getInstance().getAllRecord(MarkParcel.class);
        paymentSystemTypes = DeliveryServiceDao.getInstance().getAllRecord(PaymentsSystemType.class);
        offices = DeliveryServiceDao.getInstance().getAllRecord(Office.class);

        fillComboBox(clientComboBox, clients);
        fillComboBox(markParcelComboBox, markParcels);
        fillComboBox(paymentsSystemTypeComboBox, paymentSystemTypes);
        fillComboBox(officeComboBox, offices);
    }

    private Parcel getParcel() {
        Date acceptanceDate = acceptanceDateField.getDate();
        Date payDate = payDateField.getDate();

        Client client = clients.get(clientComboBox.getSelectedIndex());
        MarkParcel markParsel = markParcels.get(markParcelComboBox.getSelectedIndex());
        PaymentsSystemType paymentsSystemType = paymentSystemTypes.get(paymentsSystemTypeComboBox.getSelectedIndex());
        Office office = offices.get(officeComboBox.getSelectedIndex());
        int sumValue = ((Float) sumField.getValue()).intValue();

        int height = (int) heightSpinner.getValue();
        int weigth = (int) weigthSpinner.getValue();
        int depth = (int) depthSpinner.getValue();
        int width = (int) widthSpinner.getValue();

        Payment payment = new Payment();
        payment.setPaymentssystemtype(paymentsSystemType);
        payment.setPayDate(payDate);
        payment.setSum(sumValue);
        
        DeliveryServiceDao.getInstance().addRecord(payment);

        Parcel parcel = new Parcel();
        parcel.setPayment(payment);
        parcel.setClient(client);
        parcel.setMarkparcel(markParsel);

        parcel.setAcceptanceDate(acceptanceDate);
        parcel.setDepth(depth);
        parcel.setHeight(height);
        parcel.setWeight(weigth);
        parcel.setWidth(width);
        
        parcel.setOffice(office);

        return parcel;
    }

    private void initialaze() {
        this.setSize(740, 470);
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

            MarkParcel markParcel = markParcels.get(markParcelComboBox.getSelectedIndex());

            int cost = height * lastRate.getHeigth() + weigth * lastRate.getWeigth() + depth * lastRate.getDepth()
                    + width * lastRate.getWidth();

            sumField.setValue(cost * markParcel.getRate());
        }
    }
}
