package by.bsuir.verkpavel.adb.ui.deposit;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.Format;
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
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import by.bsuir.verkpavel.adb.data.DataProvider;
import by.bsuir.verkpavel.adb.data.entity.Deposit;
import by.bsuir.verkpavel.adb.resources.RussianStrings;
import by.bsuir.verkpavel.adb.ui.ActionMode;

public class ActionView extends JFrame {
    private static final long serialVersionUID = 2883993883146596569L;
    private static final Calendar  maxDate = Calendar.getInstance();
    
    protected JPanel mainPanel;
    private JTextField contractNumberField;
    private JTextField depositPeriodTextField;

    protected JFormattedTextField startDateField;
    protected JFormattedTextField depositSumField;

    protected JComboBox<String> depositTypeComboBox;
    protected JComboBox<String> moneyTypeComboBox;
    private SimpleDateFormat dateMask;

    protected static Deposit currentDeposit;
    private JFormattedTextField endDateField;
    private JLabel endDatelabel;
    private JLabel persentlabel;
    private JFormattedTextField persentTextField;

    private static void initialaze(ActionView actionView) {
        ActionView frame = null;
        frame = actionView;
        frame.setSize(690, 480);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setVisible(true);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                ShowDepositsView.create();
            }
        });
        
        maxDate.set(Calendar.YEAR, 1900);
        maxDate.set(Calendar.MONTH, Calendar.JANUARY);
        maxDate.set(Calendar.DAY_OF_MONTH, 1);
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

        JLabel startDate_label = new JLabel(
                "Дата начала");
        startDate_label.setBounds(23, 72, 126, 14);
        mainPanel.add(startDate_label);

        JLabel depositType_label = new JLabel(
                "Вид депозита");
        depositType_label.setBounds(23, 16, 141, 16);
        mainPanel.add(depositType_label);

        JLabel contractNumberlbl = new JLabel(
                "Номер договора");
        contractNumberlbl.setBounds(197, 16, 148, 16);
        mainPanel.add(contractNumberlbl);

        JLabel depositPeriodLabel = new JLabel(
                "Срок договора");
        depositPeriodLabel.setBounds(168, 98, 148, 16);
        mainPanel.add(depositPeriodLabel);

        JLabel moneyTypeLabel = new JLabel(
                "Вид валюты");
        moneyTypeLabel.setBounds(23, 114, 126, 16);
        mainPanel.add(moneyTypeLabel);

        JLabel depositSumLabel = new JLabel("Сумма вклада");
        depositSumLabel.setBounds(24, 195, 126, 16);
        mainPanel.add(depositSumLabel);
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
    	moneyTypeComboBox.setSelectedIndex(deposit.nationality - 1);
    	
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
        int moneyType = moneyTypeComboBox.getSelectedIndex();
        int depositSum = ((Double) depositSumField.getValue()).intValue();
        
        double persent = (Double) persentTextField.getValue();
        

        if (checkRequiredFields(startDate, endDate, depositType, contractNumber, depositPeriod, moneyType, persent, depositType)) {
            if (((Date) startDateField.getValue()).before(new Date())
                    || ((Date) endDateField.getValue()).before((Date) startDateField.getValue())) {
                JOptionPane.showMessageDialog(null, RussianStrings.DATE_AFTER_NOW.get(),
                        "Error", JOptionPane.PLAIN_MESSAGE);
                return null;
            }
            
            if (((Date) startDateField.getValue()).after(maxDate.getTime())
                    || ((Date) endDateField.getValue()).after(maxDate.getTime())) {
                JOptionPane.showMessageDialog(null, RussianStrings.DATE_BEFORE_01011900.get(),
                        "Error", JOptionPane.PLAIN_MESSAGE);
                return null;
            }
            return new Deposit(startDate, depositType, contractNumber, depositPeriod, moneyType, depositSum);
        }
        return null;
    }

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
        startDateField.setBounds(148, 72, 92, 28);
        mainPanel.add(startDateField);

        depositTypeComboBox = new JComboBox<String>();
        depositTypeComboBox.setBounds(23, 34, 126, 26);
        mainPanel.add(depositTypeComboBox);

        contractNumberField = new JTextField();
        contractNumberField.setBounds(193, 34, 365, 28);
        mainPanel.add(contractNumberField);
        contractNumberField.setColumns(10);

        depositPeriodTextField = new JTextField();
        depositPeriodTextField.setColumns(10);
        depositPeriodTextField.setBounds(167, 114, 272, 28);
        mainPanel.add(depositPeriodTextField);

        moneyTypeComboBox = new JComboBox<String>();
        moneyTypeComboBox.setBounds(23, 132, 126, 26);
        mainPanel.add(moneyTypeComboBox);

        MaskFormatter passportidertIfyNumberMask = new MaskFormatter("#######U###UU#");

        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        format.setMaximumFractionDigits(0);
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setMinimum(0.0);
        formatter.setMaximum(10000000.0);
        formatter.setAllowsInvalid(false);
        formatter.setOverwriteMode(true);
        depositSumField = new JFormattedTextField(formatter);
        depositSumField.setBounds(158, 194, 114, 20);
        mainPanel.add(depositSumField);
        depositSumField.setColumns(10);
        depositSumField.setValue(0.0);
        
        //TODO Add format
        endDateField = new JFormattedTextField((Format) null);
        endDateField.setBounds(403, 66, 92, 28);
        mainPanel.add(endDateField);
        
        endDatelabel = new JLabel("Дата окончания");
        endDatelabel.setBounds(265, 72, 126, 14);
        mainPanel.add(endDatelabel);
        
        persentlabel = new JLabel("Проценты");
        persentlabel.setBounds(294, 194, 102, 16);
        mainPanel.add(persentlabel);
        
        //TODO Add format
        persentTextField = new JFormattedTextField((Format) null);
        persentTextField.setColumns(10);
        persentTextField.setBounds(404, 193, 114, 20);
        mainPanel.add(persentTextField);

    }

    private void fillComboBoxes() {
        fillComboBox(depositTypeComboBox, DataProvider.getInstance().getCityList());
        fillComboBox(moneyTypeComboBox, DataProvider.getInstance().getNationalitys());
    }

    private void fillComboBox(JComboBox<String> target, ArrayList<String> source) {
        for (String item : source) {
            target.addItem(item);
        }
    }
}
