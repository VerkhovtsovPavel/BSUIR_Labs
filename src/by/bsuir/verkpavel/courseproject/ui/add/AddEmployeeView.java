package by.bsuir.verkpavel.courseproject.ui.add;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import org.jdesktop.swingx.JXDatePicker;

import by.bsuir.verkpavel.courseproject.dao.entity.Client;
import by.bsuir.verkpavel.courseproject.resources.ProjectProperties;

public class AddEmployeeView extends JFrame {
    private static final long serialVersionUID = 2883993883146596569L;
    private static final Calendar minDate = Calendar.getInstance();

    private JPanel mainPanel;

    private JTextField fullNameField;
    private JTextField emailField;
    private JTextField userNameField;
    private JTextField passwordField;

    private JXDatePicker bornDateField;
    private JFormattedTextField hireDateField;
    private JFormattedTextField mobilePhoneField;
    private JFormattedTextField passportNumberField;
    private JFormattedTextField idertifyNumberField;
    private JFormattedTextField salaryField;
    private JComboBox<String> positionComboBox;
    private JComboBox<String> permissionComboBox;
    private JComboBox<String> officeComboBox;
    private SimpleDateFormat dateMask;

    private void initialaze() {
        this.setSize(690, 480);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                
            }
        });

        minDate.set(Calendar.YEAR, 1900);
        minDate.set(Calendar.MONTH, Calendar.JANUARY);
        minDate.set(Calendar.DAY_OF_MONTH, 1);
    }

    public void showView() {
        initialaze();
    }

    private AddEmployeeView() {
        configureDefaultLayot();
    }

    private void createLabels() {
        JLabel fullNameLbl = new JLabel("Ф.И.О.");
        fullNameLbl.setBounds(16, 18, 55, 14);
        mainPanel.add(fullNameLbl);

        JLabel birthday_label = new JLabel("Дата рождения");
        birthday_label.setBounds(16, 43, 102, 14);
        mainPanel.add(birthday_label);

        JLabel mobilePhonelabel = new JLabel("Моб. телефон");
        mobilePhonelabel.setBounds(16, 110, 86, 16);
        mainPanel.add(mobilePhonelabel);

        JLabel emailLbl = new JLabel("E-mail");
        emailLbl.setBounds(309, 65, 55, 16);
        mainPanel.add(emailLbl);

        JLabel hireDateLbl = new JLabel("Даты приема на работу");
        hireDateLbl.setBounds(132, 43, 157, 14);
        mainPanel.add(hireDateLbl);

        JLabel positionLabel = new JLabel(
                "Должность");
        positionLabel.setBounds(20, 153, 133, 16);
        mainPanel.add(positionLabel);

        JLabel permissionsLabel = new JLabel("Права доступа");
        permissionsLabel.setBounds(163, 153, 126, 16);
        mainPanel.add(permissionsLabel);

        JLabel officeLabel = new JLabel("Офис");
        officeLabel.setBounds(299, 153, 76, 16);
        mainPanel.add(officeLabel);

        JLabel userNameLbl = new JLabel("Имя пользователя");
        userNameLbl.setBounds(16, 241, 126, 14);
        mainPanel.add(userNameLbl);

        JLabel passwordLabel = new JLabel(
                "Пароль");
        passwordLabel.setBounds(16, 271, 102, 14);
        mainPanel.add(passwordLabel);

        JLabel salaryLabel = new JLabel("Зарплата");
        salaryLabel.setBounds(463, 176, 68, 16);
        mainPanel.add(salaryLabel);
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

    protected Client getEmployee() {
        String fullName = fullNameField.getText();
        Date bornDate = bornDateField.getDate();

        String passportNumber = passportNumberField.getText();
        String whoGivePassport = userNameField.getText();
        String passportTakeDate = hireDateField.getText();
        String identifyNumber = idertifyNumberField.getText();
        String bornPlace = passwordField.getText();


        String mobilePhone = mobilePhoneField.getText();
        String eMail = emailField.getText();


        int familyStatus = positionComboBox.getSelectedIndex();
        int nationality = permissionComboBox.getSelectedIndex();
        int disability = officeComboBox.getSelectedIndex();

        int salary = ((Double) salaryField.getValue()).intValue();


           
        return null;
    }



    private void createActionElements()  {
        fullNameField = new JTextField();
        fullNameField.setColumns(10);
        fullNameField.setBounds(79, 8, 593, 28);
        fullNameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent e) {
                char key = e.getKeyChar();
                if (!ProjectProperties.getRussianAlphabet().contains("" + Character.toLowerCase(key))) {
                    e.consume();
                }
            }
        });
        mainPanel.add(fullNameField);

        dateMask = ProjectProperties.getDateFormatter();

        bornDateField = new JXDatePicker();
        bornDateField.setValue(new java.util.Date());
        bornDateField.setBounds(156, 67, 174, 38);
        mainPanel.add(bornDateField);

        MaskFormatter phoneNumberMask = ProjectProperties.getPhoneNumberMask();

        mobilePhoneField = new JFormattedTextField(phoneNumberMask);
        mobilePhoneField.setColumns(10);
        mobilePhoneField.setBounds(112, 104, 259, 28);
        mainPanel.add(mobilePhoneField);

        emailField = new JTextField();
        emailField.setBounds(386, 59, 286, 28);
        emailField.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
                if (!emailField.getText().trim().matches("^([\\w\\.\\-_]+)?\\w+@[\\w-_]+(\\.\\w+){1,}$")) {
                    emailField.setText("");
                }
            }

            @Override
            public void focusGained(FocusEvent e) {

            }
        });
        mainPanel.add(emailField);
        emailField.setColumns(10);

        positionComboBox = new JComboBox<String>();
        positionComboBox.setBounds(10, 171, 126, 26);
        mainPanel.add(positionComboBox);

        permissionComboBox = new JComboBox<String>();
        permissionComboBox.setBounds(163, 171, 126, 26);
        mainPanel.add(permissionComboBox);

        officeComboBox = new JComboBox<String>();
        officeComboBox.setBounds(299, 171, 126, 26);
        mainPanel.add(officeComboBox);

        hireDateField = new JFormattedTextField(dateMask);
        hireDateField.setBounds(132, 61, 92, 28);
        hireDateField.setValue(new java.util.Date());
        mainPanel.add(hireDateField);

        userNameField = new JTextField();
        userNameField.setColumns(10);
        userNameField.setBounds(160, 232, 502, 28);
        mainPanel.add(userNameField);

        passwordField = new JTextField();
        passwordField.setColumns(10);
        passwordField.setBounds(160, 267, 502, 28);
        mainPanel.add(passwordField);

        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        format.setMaximumFractionDigits(0);
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setMinimum(0.0);
        formatter.setMaximum(10000000.0);
        formatter.setAllowsInvalid(false);
        formatter.setOverwriteMode(true);
        salaryField = new JFormattedTextField(formatter);
        salaryField.setBounds(532, 171, 114, 26);
        mainPanel.add(salaryField);
        salaryField.setColumns(10);
        salaryField.setValue(0.0);
        
        JButton addButton = new JButton("Добавить");
        addButton.setBounds(282, 327, 89, 23);
        mainPanel.add(addButton);

    }
}
