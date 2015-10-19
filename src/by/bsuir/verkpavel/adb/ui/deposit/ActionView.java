package by.bsuir.verkpavel.adb.ui.deposit;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Properties;

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

import org.jdatepicker.AbstractDateModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;

import by.bsuir.verkpavel.adb.data.DataProvider;
import by.bsuir.verkpavel.adb.data.entity.Deposit;
import by.bsuir.verkpavel.adb.resources.RussianStrings;
import by.bsuir.verkpavel.adb.ui.ActionMode;

//TODO Check field formats
public abstract class ActionView extends JFrame {
    private static final long serialVersionUID = 2883993883146596569L;
    private static final LocalDate maxDate = LocalDate.of(2100, 1, 1);

    protected static Deposit currentDeposit;

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

    private DateTimeFormatter dateMask;

    private static void initialaze(ActionView actionView) {
        ActionView frame = null;
        frame = actionView;
        frame.setSize(750, 350);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                ShowDepositsView.create();
            }
        });
    }

    public static void create(ActionMode mode, Deposit deposit) {
        try {
            currentDeposit = deposit;
            ActionView actionView = null;
            switch (mode) {
            case ADD:
                actionView = new AddView();
                break;
            case SHOW:
                actionView = new ShowView();
                break;
            case EDIT:
                break;
            default:
                break;
            }
            initialaze(actionView);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    protected ActionView() throws ParseException {
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

        startDateField.setValue(dateMask.parse(deposit.startDate));
        endDateField.setValue(dateMask.parse(deposit.endDate));
        depositPeriodTextField.setText("" + deposit.depositPeriod);

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

        // TODO Add check end > start
        long depositPeriod = ChronoUnit.DAYS.between(LocalDate.parse(startDate, dateMask),
                LocalDate.parse(endDate, dateMask));
        int currency = currencyComboBox.getSelectedIndex();
        int depositSum = ((Double) depositSumField.getValue()).intValue();

        float persent = (float) persentTextField.getValue();
        // TODO Change message text
        if (checkRequiredFields(startDate, endDate, contractNumber)) {
            if (((LocalDate) startDateField.getValue()).isAfter(LocalDate.now())
                    || ((LocalDate) endDateField.getValue()).isBefore((LocalDate) startDateField
                            .getValue())) {
                JOptionPane.showMessageDialog(null, RussianStrings.DATE_AFTER_NOW.get(), "Error",
                        JOptionPane.PLAIN_MESSAGE);
                return null;
            }

            if (((LocalDate) startDateField.getValue()).isAfter(maxDate)
                    || ((LocalDate) endDateField.getValue()).isAfter(maxDate)) {
                JOptionPane.showMessageDialog(null, RussianStrings.DATE_BEFORE_01011900.get(),
                        "Error", JOptionPane.PLAIN_MESSAGE);
                return null;
            }
            return new Deposit(contractNumber, depositType, startDate, endDate, depositPeriod,
                    currency, persent, depositSum, /* TODO Add real client id */1);
        }
        return null;
    }

    private boolean checkRequiredFields(String... fields) {
        for (String field : fields) {
            if (field.trim().isEmpty())
                return false;
        }
        return true;
    }

    private void createActionElements() throws ParseException {
        dateMask = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        AbstractDateModel<LocalDate> model = new AbstractDateModel<LocalDate>() {

            @Override
            protected LocalDate fromCalendar(Calendar cal) {
                return LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
            }

            @Override
            protected Calendar toCalendar(LocalDate ld) {
                Calendar cal = Calendar.getInstance();
                cal.set(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
                return cal;
            }
        };
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        //TODO Investigate side effect
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        //TODO Move to field
        JDatePickerImpl startDateField = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        model.setValue(LocalDate.now());
        model.getValue();
        startDateField.setBounds(114, 149, 92, 28);
        mainPanel.add(startDateField);

        endDateField = new JFormattedTextField(dateMask);
        endDateField.setValue(LocalDate.now().plusDays(1));
        endDateField.setBounds(322, 149, 92, 28);
        mainPanel.add(endDateField);

        depositTypeComboBox = new JComboBox<String>();
        depositTypeComboBox.setBounds(23, 34, 126, 26);
        mainPanel.add(depositTypeComboBox);

        // TODO Change to real contact number mask
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

        NumberFormat persentFormat = NumberFormat.getPercentInstance();
        persentFormat.setMaximumFractionDigits(2);
        NumberFormatter persentsFormatter = new NumberFormatter(persentFormat);
        persentsFormatter.setMinimum((float)0.0001);
        persentsFormatter.setMaximum((float)3);
        persentTextField = new JFormattedTextField(persentsFormatter);
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
