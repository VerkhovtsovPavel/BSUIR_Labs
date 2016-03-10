package by.bsuir.verkpavel.adb.ui.client;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import by.bsuir.verkpavel.adb.data.ClientProvider;
import by.bsuir.verkpavel.adb.data.entity.Client;
import by.bsuir.verkpavel.adb.resources.Messages;
import by.bsuir.verkpavel.adb.ui.ActionMode;

public class ActionView extends JFrame {
    private static final long serialVersionUID = 2883993883146596569L;
    private static final Calendar  minDate = Calendar.getInstance();
    
    protected JPanel mainPanel;

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField middleNameField;
    private JTextField realAddressField;
    private JTextField officialAddressTextField;
    private JTextField emailField;
    private JTextField passportSeriesField;
    private JTextField whoGivePassportField;
    private JTextField bornPlaceField;

    private JFormattedTextField bornDateField;
    private JFormattedTextField passportTakeDateField;
    private JFormattedTextField homePhoneField;
    private JFormattedTextField mobilePhoneField;
    private JFormattedTextField passportNumberField;
    private JFormattedTextField idertifyNumberField;
    private JFormattedTextField salaryField;

    private JRadioButton manRadioButton;
    private JRadioButton womanRadioButton;

    private JComboBox<String> realCityComboBox;
    private JComboBox<String> familyStatusComboBox;
    private JComboBox<String> nationalityComboBox;
    private JComboBox<String> disabilityComboBox;

    private JCheckBox pensionerCheckBox;
    private SimpleDateFormat dateMask;

    protected static Client currentClient;

    private static void initialaze(ActionView actionView) {
        ActionView frame = null;
        frame = actionView;
        frame.setSize(690, 480);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                ShowUsersView.create();
            }
        });
        
        minDate.set(Calendar.YEAR, 1900);
        minDate.set(Calendar.MONTH, Calendar.JANUARY);
        minDate.set(Calendar.DAY_OF_MONTH, 1);
    }

    public static void create(ActionMode mode, Client client) {
        try {
            currentClient = client;
            ActionView actionView = null;
            switch (mode) {
            case ADD:
                actionView = new AddView();
                break;
            case EDIT:
                actionView = new EditView();
                break;
            case SHOW:
                actionView = new ShowView();
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
        JLabel firstNameLbl = new JLabel("\u0424\u0430\u043C\u0438\u043B\u0438\u044F");
        firstNameLbl.setBounds(10, 11, 66, 14);
        mainPanel.add(firstNameLbl);

        JLabel FirstNamelbl = new JLabel("\u0418\u043C\u044F");
        FirstNamelbl.setBounds(289, 11, 49, 14);
        mainPanel.add(FirstNamelbl);

        JLabel MiddleNamelbl = new JLabel("\u041E\u0442\u0447\u0435\u0441\u0442\u0432\u043E");
        MiddleNamelbl.setBounds(427, 11, 66, 14);
        mainPanel.add(MiddleNamelbl);

        JLabel sex_label = new JLabel("\u041F\u043E\u043B");
        sex_label.setBounds(16, 42, 46, 14);
        mainPanel.add(sex_label);

        JLabel birthday_label = new JLabel(
                "\u0414\u0430\u0442\u0430 \u0440\u043E\u0436\u0434\u0435\u043D\u0438\u044F");
        birthday_label.setBounds(131, 40, 126, 14);
        mainPanel.add(birthday_label);

        JLabel city_label = new JLabel(
                "\u0413\u043E\u0440\u043E\u0434 \u043F\u0440\u043E\u0436\u0438\u0432\u0430\u043D\u0438\u044F");
        city_label.setBounds(252, 42, 141, 16);
        mainPanel.add(city_label);

        JLabel realAddress_lbl = new JLabel(
                "\u0410\u0434\u0440\u0435\u0441 \u043F\u0440\u043E\u0436\u0438\u0432\u0430\u043D\u0438\u044F");
        realAddress_lbl.setBounds(399, 42, 148, 16);
        mainPanel.add(realAddress_lbl);

        JLabel officialAddressLabel = new JLabel(
                "\u0410\u0434\u0440\u0435\u0441 \u043F\u0440\u043E\u043F\u0438\u0441\u043A\u0438");
        officialAddressLabel.setBounds(399, 98, 148, 16);
        mainPanel.add(officialAddressLabel);

        JLabel homePhonelabel = new JLabel(
                "\u0414\u043E\u043C. \u0442\u0435\u043B\u0435\u0444\u043E\u043D");
        homePhonelabel.setBounds(10, 121, 102, 16);
        mainPanel.add(homePhonelabel);

        JLabel mobilePhonelabel = new JLabel(
                "\u041C\u043E\u0431. \u0442\u0435\u043B\u0435\u0444\u043E\u043D");
        mobilePhonelabel.setBounds(10, 161, 102, 16);
        mainPanel.add(mobilePhonelabel);

        JLabel emailLbl = new JLabel("E-mail");
        emailLbl.setBounds(399, 145, 55, 16);
        mainPanel.add(emailLbl);

        JLabel keepDateLbl = new JLabel(
                "\u0414\u0430\u0442\u0430 \u0432\u044B\u0434\u0430\u0447\u0438");
        keepDateLbl.setBounds(10, 290, 102, 14);
        mainPanel.add(keepDateLbl);

        JLabel passSeriesLabel = new JLabel(
                "\u0421\u0435\u0440\u0438\u044F \u043F\u0430\u0441\u043F\u043E\u0440\u0442\u0430");
        passSeriesLabel.setBounds(0, 266, 115, 14);
        mainPanel.add(passSeriesLabel);

        JLabel familyStatusLabel = new JLabel(
                "\u0421\u0435\u043C\u0435\u0439\u043D\u043E\u0435 \u043F\u043E\u043B\u043E\u0436\u0435\u043D\u0438\u0435");
        familyStatusLabel.setBounds(0, 205, 156, 16);
        mainPanel.add(familyStatusLabel);

        JLabel nationalityLabel = new JLabel(
                "\u0413\u0440\u0430\u0436\u0434\u0430\u043D\u0441\u0442\u0432\u043E");
        nationalityLabel.setBounds(163, 205, 126, 16);
        mainPanel.add(nationalityLabel);

        JLabel passNumberLabel = new JLabel(
                "\u041D\u043E\u043C\u0435\u0440 \u043F\u0430\u0441\u043F\u043E\u0440\u0442\u0430");
        passNumberLabel.setBounds(161, 266, 128, 14);
        mainPanel.add(passNumberLabel);

        JLabel disabilityLabel = new JLabel(
                "\u0418\u043D\u0432\u0430\u043B\u0438\u0434\u043D\u043E\u0441\u0442\u044C");
        disabilityLabel.setBounds(289, 205, 126, 16);
        mainPanel.add(disabilityLabel);

        JLabel indentNumberLabel = new JLabel(
                "\u0418\u043D\u0434\u0435\u0442. \u043D\u043E\u043C\u0435\u0440");
        indentNumberLabel.setBounds(376, 266, 102, 14);
        mainPanel.add(indentNumberLabel);

        JLabel whoGiveLbl = new JLabel("\u041A\u0435\u043C \u0432\u044B\u0434\u0430\u043D");
        whoGiveLbl.setBounds(119, 290, 102, 14);
        mainPanel.add(whoGiveLbl);

        JLabel bornPlaceLabel = new JLabel(
                "\u041C\u0435\u0441\u0442\u043E \u0440\u043E\u0436\u0434\u0435\u043D\u0438\u044F");
        bornPlaceLabel.setBounds(10, 348, 146, 14);
        mainPanel.add(bornPlaceLabel);

        JLabel salaryLabel = new JLabel("\u0417\u0430\u0440\u043F\u043B\u0430\u0442\u0430");
        salaryLabel.setBounds(10, 392, 102, 16);
        mainPanel.add(salaryLabel);
    }

    private void configureDefaultLayot() throws ParseException {
        initialazeLayout();
        createElements();
        fillComboBoxes();
        customActions();
    }

    protected void customActions() throws ParseException {

    }

    protected void fillFields(Client client) throws ParseException {
        firstNameField.setText(client.firstName);
        lastNameField.setText(client.lastName);
        middleNameField.setText(client.middleName);
        realAddressField.setText(client.realAddress);
        officialAddressTextField.setText(client.officialAddress);
        emailField.setText(client.eMail);
        passportSeriesField.setText(client.passportSeries);
        whoGivePassportField.setText(client.whoGivePassport);
        bornPlaceField.setText(client.bornPlace);

        bornDateField.setValue(dateMask.parseObject(client.bornDate));
        passportTakeDateField.setValue(dateMask.parseObject(client.passportTakeDate));
        if (!client.homePhone.contains("+(   )-  -   -    "))
            homePhoneField.setValue(client.homePhone);
        if (!client.mobilePhone.contains("+(   )-  -   -    "))
            mobilePhoneField.setValue(client.mobilePhone);
        passportNumberField.setValue(client.passportNumber);
        idertifyNumberField.setValue(client.identifyNumber);
        salaryField.setValue((double) client.salary);

        manRadioButton.setSelected(client.isMan);
        womanRadioButton.setSelected(!client.isMan);

        realCityComboBox.setSelectedIndex(client.realCity - 1);
        familyStatusComboBox.setSelectedIndex(client.familyStatus - 1);
        nationalityComboBox.setSelectedIndex(client.nationality - 1);
        disabilityComboBox.setSelectedIndex(client.disability - 1);

        pensionerCheckBox.setSelected(client.pensioner);
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

	protected Client getClient() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String middleName = middleNameField.getText();
        String bornDate = bornDateField.getText();

        boolean isMan = manRadioButton.isSelected();

        String passportSeries = passportSeriesField.getText();
        String passportNumber = passportNumberField.getText();
        String whoGivePassport = whoGivePassportField.getText();
        String passportTakeDate = passportTakeDateField.getText();
        String identifyNumber = idertifyNumberField.getText();
        String bornPlace = bornPlaceField.getText();

        int realCity = realCityComboBox.getSelectedIndex();
        String realAddress = realAddressField.getText();
        String homePhone = homePhoneField.getText();
        String mobilePhone = mobilePhoneField.getText();
        String eMail = emailField.getText();

        String officialAddress = officialAddressTextField.getText();
        int familyStatus = familyStatusComboBox.getSelectedIndex();
        int nationality = nationalityComboBox.getSelectedIndex();
        int disability = disabilityComboBox.getSelectedIndex();
        boolean pensioner = pensionerCheckBox.isSelected();

        int salary = ((Double) salaryField.getValue()).intValue();

        if (checkRequiredFields(firstName, lastName, middleName, bornDate, passportSeries,
                passportNumber, whoGivePassport, passportTakeDate, identifyNumber, bornPlace,
                realCity, realAddress, officialAddress, familyStatus, nationality, disability)) {
            if (((Date) bornDateField.getValue()).after(new Date())
                    || ((Date) passportTakeDateField.getValue()).after(new Date())) {
                JOptionPane.showMessageDialog(null, Messages.DATE_AFTER_NOW.get(),
                        "Error", JOptionPane.PLAIN_MESSAGE);
                return null;
            }
            
            if (((Date) bornDateField.getValue()).before(minDate.getTime())
                    || ((Date) passportTakeDateField.getValue()).before(minDate.getTime())) {
                JOptionPane.showMessageDialog(null, Messages.DATE_BEFORE_MIN_DATE.get(),
                        "Error", JOptionPane.PLAIN_MESSAGE);
                return null;
            }
            return new Client(firstName, lastName, middleName, bornDate, isMan, passportSeries,
                    passportNumber, whoGivePassport, passportTakeDate, identifyNumber, bornPlace,
                    realCity, realAddress, homePhone, mobilePhone, eMail, officialAddress,
                    familyStatus, nationality, disability, pensioner, salary);
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
        firstNameField = new JTextField();
        firstNameField.setColumns(10);
        firstNameField.setBounds(79, 8, 210, 23);
        firstNameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent e) {
                char key = e.getKeyChar();
                if (!Messages.RUSSIAN_ALPHABET.get().contains(""+Character.toLowerCase(key))) {
                  e.consume();
                }
              }
            });
        mainPanel.add(firstNameField);

        lastNameField = new JTextField();
        lastNameField.setColumns(10);
        lastNameField.setBounds(325, 8, 92, 23);
        mainPanel.add(lastNameField);

        middleNameField = new JTextField();
        middleNameField.setColumns(10);
        middleNameField.setBounds(498, 8, 172, 23);
        mainPanel.add(middleNameField);

        manRadioButton = new JRadioButton("\u041C\u0443\u0436\u0441\u043A\u043E\u0439");
        manRadioButton.setBounds(16, 60, 92, 23);
        manRadioButton.setSelected(true);
        mainPanel.add(manRadioButton);

        womanRadioButton = new JRadioButton("\u0416\u0435\u043D\u0441\u043A\u0438\u0439");
        womanRadioButton.setBounds(16, 85, 109, 23);
        mainPanel.add(womanRadioButton);

        ButtonGroup sexRadioButtonGroup = new ButtonGroup();
        sexRadioButtonGroup.add(manRadioButton);
        sexRadioButtonGroup.add(womanRadioButton);

        dateMask = new SimpleDateFormat("yyyy-MM-dd");
        bornDateField = new JFormattedTextField(dateMask);
        bornDateField.setValue(new java.util.Date());
        bornDateField.setBounds(131, 58, 92, 28);
        mainPanel.add(bornDateField);

        realCityComboBox = new JComboBox<String>();
        realCityComboBox.setBounds(252, 60, 126, 26);
        mainPanel.add(realCityComboBox);

        realAddressField = new JTextField();
        realAddressField.setBounds(398, 58, 272, 28);
        mainPanel.add(realAddressField);
        realAddressField.setColumns(10);

        officialAddressTextField = new JTextField();
        officialAddressTextField.setColumns(10);
        officialAddressTextField.setBounds(398, 114, 272, 28);
        mainPanel.add(officialAddressTextField);

        MaskFormatter phoneNumberMask = new MaskFormatter("+(###)-##-###-####");
        homePhoneField = new JFormattedTextField(phoneNumberMask);
        homePhoneField.setBounds(119, 114, 259, 28);
        mainPanel.add(homePhoneField);
        homePhoneField.setColumns(10);

        mobilePhoneField = new JFormattedTextField(phoneNumberMask);
        mobilePhoneField.setColumns(10);
        mobilePhoneField.setBounds(119, 154, 259, 28);
        mainPanel.add(mobilePhoneField);

        emailField = new JTextField();
        emailField.setBounds(399, 161, 271, 28);
        emailField.addFocusListener(new FocusListener() {
            
            @Override
            public void focusLost(FocusEvent e) {
                if(!emailField.getText().trim().matches("^([\\w\\.\\-_]+)?\\w+@[\\w-_]+(\\.\\w+){1,}$")){
                    emailField.setText("");
                }
            }
            
            @Override
            public void focusGained(FocusEvent e) {
                
            }
        });
        mainPanel.add(emailField);
        emailField.setColumns(10);

        familyStatusComboBox = new JComboBox<String>();
        familyStatusComboBox.setBounds(10, 223, 126, 26);
        mainPanel.add(familyStatusComboBox);

        nationalityComboBox = new JComboBox<String>();
        nationalityComboBox.setBounds(163, 223, 126, 26);
        mainPanel.add(nationalityComboBox);

        disabilityComboBox = new JComboBox<String>();
        disabilityComboBox.setBounds(299, 223, 126, 26);
        mainPanel.add(disabilityComboBox);

        pensionerCheckBox = new JCheckBox("\u041F\u0435\u043D\u0441\u0438\u043E\u043D\u0435\u0440");
        pensionerCheckBox.setBounds(443, 227, 104, 18);
        mainPanel.add(pensionerCheckBox);

        passportTakeDateField = new JFormattedTextField(dateMask);
        passportTakeDateField.setBounds(10, 308, 92, 28);
        passportTakeDateField.setValue(new java.util.Date());
        mainPanel.add(passportTakeDateField);

        MaskFormatter passportSeriesMask = new MaskFormatter("UU");
        passportSeriesField = new JFormattedTextField(passportSeriesMask);
        passportSeriesField.setColumns(10);
        passportSeriesField.setBounds(121, 260, 35, 23);
        mainPanel.add(passportSeriesField);

        MaskFormatter passportNumberMask = new MaskFormatter("#######");
        passportNumberField = new JFormattedTextField(passportNumberMask);
        passportNumberField.setColumns(10);
        passportNumberField.setBounds(281, 262, 88, 23);
        mainPanel.add(passportNumberField);

        MaskFormatter passportidertIfyNumberMask = new MaskFormatter("#######U###UU#");
        idertifyNumberField = new JFormattedTextField(passportidertIfyNumberMask);
        idertifyNumberField.setColumns(10);
        idertifyNumberField.setBounds(479, 262, 191, 23);
        mainPanel.add(idertifyNumberField);

        whoGivePassportField = new JTextField();
        whoGivePassportField.setColumns(10);
        whoGivePassportField.setBounds(119, 310, 530, 23);
        mainPanel.add(whoGivePassportField);

        bornPlaceField = new JTextField();
        bornPlaceField.setColumns(10);
        bornPlaceField.setBounds(159, 345, 490, 23);
        mainPanel.add(bornPlaceField);

        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        format.setMaximumFractionDigits(0);
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setMinimum(0.0);
        formatter.setMaximum(10000000.0);
        formatter.setAllowsInvalid(false);
        formatter.setOverwriteMode(true);
        salaryField = new JFormattedTextField(formatter);
        salaryField.setBounds(120, 391, 114, 20);
        mainPanel.add(salaryField);
        salaryField.setColumns(10);
        salaryField.setValue(0.0);

    }

    private void fillComboBoxes() {
        fillComboBox(realCityComboBox, ClientProvider.getInstance().getCityList());
        fillComboBox(familyStatusComboBox, ClientProvider.getInstance().getFamilyStatuses());
        fillComboBox(nationalityComboBox, ClientProvider.getInstance().getNationalitys());
        fillComboBox(disabilityComboBox, ClientProvider.getInstance().getDisabilitys());
    }

    private void fillComboBox(JComboBox<String> target, ArrayList<String> source) {
        for (String item : source) {
            target.addItem(item);
        }
    }
}
