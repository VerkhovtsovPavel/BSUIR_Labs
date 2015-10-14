package by.bsuir.verkpavel.adb.ui.account;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import by.bsuir.verkpavel.adb.data.DataProvider;
import by.bsuir.verkpavel.adb.data.entity.Account;
import by.bsuir.verkpavel.adb.data.entity.Deposit;
import by.bsuir.verkpavel.adb.resources.RussianStrings;
public class ShowView extends JFrame {
    private static final long serialVersionUID = 2883993883146596569L;
    private static final Calendar maxDate = Calendar.getInstance();
    protected static Account currentAccount;

    protected JPanel mainPanel;

    private JTextField depositPeriodTextField;

    private JFormattedTextField contractNumberField;
    private JFormattedTextField startDateField;
    private JFormattedTextField depositSumField;
    private JFormattedTextField persentTextField;
    private JFormattedTextField endDateField;

    private JComboBox<String> depositTypeComboBox;
    private JComboBox<String> currencyComboBox;
    private JComboBox<String> clientComboBox;

    private SimpleDateFormat dateMask;

    private static void initialaze(ShowView actionView) {
        ShowView frame = null;
        frame = actionView;
        frame.setSize(750, 350);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setVisible(true);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                ShowAccountsView.create();
            }
        });
    }

    public static void create(Account account) {
        try {
            currentAccount = account;
            ShowView actionView = new ShowView();
            initialaze(actionView);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    protected ShowView() throws ParseException {
        configureDefaultLayot();
    }

    private void createLabels() {
        JLabel startDate_label = new JLabel("Дата начала");
        startDate_label.setBounds(23, 156, 81, 14);
        mainPanel.add(startDate_label);

        JLabel depositType_label = new JLabel("Вид депозита");
        depositType_label.setBounds(23, 16, 141, 16);
        mainPanel.add(depositType_label);

        JLabel contractNumberlbl = new JLabel("Номер договора");
        contractNumberlbl.setBounds(342, 14, 148, 16);
        mainPanel.add(contractNumberlbl);

        JLabel depositPeriodLabel = new JLabel("Срок договора");
        depositPeriodLabel.setBounds(424, 155, 81, 16);
        mainPanel.add(depositPeriodLabel);

        JLabel currencyLabel = new JLabel("Вид валюты");
        currencyLabel.setBounds(174, 16, 126, 16);
        mainPanel.add(currencyLabel);

        JLabel depositSumLabel = new JLabel("Сумма вклада");
        depositSumLabel.setBounds(23, 210, 92, 16);
        mainPanel.add(depositSumLabel);

        JLabel endDatelabel = new JLabel("Дата окончания");
        endDatelabel.setBounds(229, 156, 92, 14);
        mainPanel.add(endDatelabel);

        JLabel persentlabel = new JLabel("Проценты");
        persentlabel.setBounds(268, 210, 72, 16);
        mainPanel.add(persentlabel);

        JLabel clientLabel = new JLabel("Клиент");
        clientLabel.setBounds(23, 87, 46, 14);
        mainPanel.add(clientLabel);
    }

    private void configureDefaultLayot() throws ParseException {
        initialazeLayout();
        createElements();
        fillComboBoxes();
        customActions();
    }

    protected void customActions() throws ParseException {

    }

    protected void fillFields(Deposit deposit) throws ParseException {
        depositTypeComboBox.setSelectedIndex(deposit.depositType - 1);
        contractNumberField.setText(deposit.contractNumber);
        currencyComboBox.setSelectedIndex(deposit.currency - 1);

        startDateField.setValue(dateMask.parseObject(deposit.startDate));
        endDateField.setValue(dateMask.parseObject(deposit.endDate));
        depositPeriodTextField.setText(deposit.depositPeriod);

        depositSumField.setValue((double) deposit.depositSum);
        persentTextField.setValue((double) deposit.persent);
    }

    private void initialazeLayout() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        mainPanel = new JPanel();
        setContentPane(mainPanel);
        mainPanel.setLayout(null);
    }

    private void createElements() throws ParseException {
        createLabels();
        createActionElements();
    }

    protected Deposit getDeposit() {
        String startDate = startDateField.getText();
        String endDate = endDateField.getText();

        int depositType = depositTypeComboBox.getSelectedIndex();
        String contractNumber = contractNumberField.getText();

        String depositPeriod = depositPeriodTextField.getText();
        int currency = currencyComboBox.getSelectedIndex();
        int depositSum = ((Double) depositSumField.getValue()).intValue();

        double persent = (Double) persentTextField.getValue();

        if (checkRequiredFields(startDate, endDate, depositType, contractNumber, depositPeriod,
                currency, persent, depositType)) {
            if (((Date) startDateField.getValue()).before(new Date())
                    || ((Date) endDateField.getValue()).before((Date) startDateField.getValue())) {
                JOptionPane.showMessageDialog(null, RussianStrings.DATE_AFTER_NOW.get(), "Error",
                        JOptionPane.PLAIN_MESSAGE);
                return null;
            }

            if (((Date) startDateField.getValue()).after(maxDate.getTime())
                    || ((Date) endDateField.getValue()).after(maxDate.getTime())) {
                JOptionPane.showMessageDialog(null, RussianStrings.DATE_BEFORE_01011900.get(),
                        "Error", JOptionPane.PLAIN_MESSAGE);
                return null;
            }
            return new Deposit(startDate, depositType, contractNumber, depositPeriod, currency,
                    depositSum);
        }
        return null;
    }
    
    //TODO Check correct work
    private boolean checkRequiredFields(Object... fields) {
        for (Object field : fields) {
            if (field instanceof String) {
                if (((String) field).trim().isEmpty())
                    return false;
            } else {
                if (((Integer) field).intValue() == -1) {
                    return false;
                }
            }
        }
        return true;
    }

    private void createActionElements() throws ParseException {
        dateMask = new SimpleDateFormat("yyyy-MM-dd");
        startDateField = new JFormattedTextField(dateMask);
        startDateField.setValue(new java.util.Date());
        startDateField.setBounds(114, 149, 92, 28);
        mainPanel.add(startDateField);

        endDateField = new JFormattedTextField(dateMask);
        endDateField.setValue(new java.util.Date());
        endDateField.setBounds(322, 149, 92, 28);
        mainPanel.add(endDateField);

        depositTypeComboBox = new JComboBox<String>();
        depositTypeComboBox.setBounds(23, 34, 126, 26);
        mainPanel.add(depositTypeComboBox);

        MaskFormatter contractNumberMask = new MaskFormatter("#######U###UU#");
        contractNumberField = new JFormattedTextField(contractNumberMask);
        contractNumberField.setBounds(338, 32, 365, 28);
        mainPanel.add(contractNumberField);
        contractNumberField.setColumns(10);

        depositPeriodTextField = new JTextField();
        depositPeriodTextField.setColumns(10);
        depositPeriodTextField.setBounds(515, 149, 134, 28);
        depositPeriodTextField.setEditable(false);
        mainPanel.add(depositPeriodTextField);

        depositSumField = new JFormattedTextField();
        depositSumField.setBounds(114, 208, 114, 20);
        mainPanel.add(depositSumField);
        depositSumField.setColumns(10);
        depositSumField.setValue(1.0);
        
        //TODO Use formatter to set minimum value 0.01
        NumberFormat persentFormat = NumberFormat.getPercentInstance();
        persentFormat.setMaximumFractionDigits(2);
        persentTextField = new JFormattedTextField(persentFormat);
        persentTextField.setColumns(10);
        persentTextField.setValue(new Float(0.056));
        persentTextField.setBounds(342, 208, 114, 20);
        mainPanel.add(persentTextField);

        clientComboBox = new JComboBox<String>();
        clientComboBox.setBounds(79, 84, 624, 26);
        mainPanel.add(clientComboBox);

        currencyComboBox = new JComboBox<String>();
        currencyComboBox.setBounds(174, 34, 126, 26);
        currencyComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String type = (String) currencyComboBox.getSelectedItem();
                NumberFormat format = null;
                switch (type) {
                case "EUR":
                    format = NumberFormat.getCurrencyInstance(Locale.GERMANY);
                    break;
                case "USD":
                    format = NumberFormat.getCurrencyInstance(Locale.US);
                    break;
                case "BYR":
                    format = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("be-BY"));
                    break;
                default:
                    format = NumberFormat.getCurrencyInstance();
                }
                format.setMaximumFractionDigits(0);
                NumberFormatter formatter = new NumberFormatter(format);
                formatter.setMinimum(1.0);
                formatter.setMaximum(10000000.0);
                formatter.setAllowsInvalid(false);
                formatter.setOverwriteMode(true);

                depositSumField.setFormatterFactory(new DefaultFormatterFactory(formatter));
            }
        });
        mainPanel.add(currencyComboBox);
    }

    private void fillComboBoxes() {
        fillComboBox(depositTypeComboBox, DataProvider.getInstance().getDepositTypeList());
        fillComboBox(currencyComboBox, DataProvider.getInstance().getCurrency());
        fillComboBox(clientComboBox, DataProvider.getInstance().getUserFullNames());
    }

    private void fillComboBox(JComboBox<String> target, ArrayList<String> source) {
        for (String item : source) {
            target.addItem(item);
        }
    }
}
