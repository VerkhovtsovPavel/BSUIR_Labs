package by.bsuir.verkpavel.courseproject.ui.add;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.JXDatePicker;

import by.bsuir.verkpavel.courseproject.dao.DeliveryServiceDao;
import by.bsuir.verkpavel.courseproject.dao.Describable;
import by.bsuir.verkpavel.courseproject.dao.entity.Authentication;
import by.bsuir.verkpavel.courseproject.dao.entity.Employee;
import by.bsuir.verkpavel.courseproject.dao.entity.Office;
import by.bsuir.verkpavel.courseproject.dao.entity.Permission;
import by.bsuir.verkpavel.courseproject.dao.entity.Position;
import by.bsuir.verkpavel.courseproject.dao.entity.Salary;
import by.bsuir.verkpavel.courseproject.resources.Messages;
import by.bsuir.verkpavel.courseproject.resources.ProjectProperties;

public class AddEmployeeView extends JFrame {
    private static final long serialVersionUID = 2883993883146596569L;

    private static Logger log = Logger.getLogger(AddEmployeeView.class);

    private JPanel mainPanel;

    private JTextField fullNameField;
    private JTextField emailField;
    private JTextField userNameField;
    private JTextField passwordField;

    private JXDatePicker bornDateField;
    private JXDatePicker hireDateField;
    private JFormattedTextField mobilePhoneField;
    private JFormattedTextField salaryField;
    private JComboBox<String> positionComboBox;
    private JComboBox<String> permissionComboBox;
    private JComboBox<String> officeComboBox;

    private List<Position> positions;

    private List<Permission> permissions;

    private List<Office> offices;

    private void initialaze() {
        this.setSize(690, 445);
        this.setTitle("Добавление работника");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        this.setVisible(true);
    }

    public void showView() {
        initialaze();
    }

    public AddEmployeeView() {
        configureDefaultLayot();
        fillComboBoxes();
    }

    private void createLabels() {
        JLabel fullNameLbl = new JLabel("Ф.И.О.");
        fullNameLbl.setBounds(16, 18, 55, 14);
        mainPanel.add(fullNameLbl);

        JLabel birthday_label = new JLabel("Дата рождения");
        birthday_label.setBounds(16, 43, 150, 14);
        mainPanel.add(birthday_label);

        JLabel mobilePhonelabel = new JLabel("Моб. телефон");
        mobilePhonelabel.setBounds(16, 110, 86, 16);
        mainPanel.add(mobilePhonelabel);

        JLabel emailLbl = new JLabel("E-mail");
        emailLbl.setBounds(335, 65, 48, 16);
        mainPanel.add(emailLbl);

        JLabel hireDateLbl = new JLabel("Даты приема на работу");
        hireDateLbl.setBounds(179, 43, 209, 14);
        mainPanel.add(hireDateLbl);

        JLabel positionLabel = new JLabel("Должность");
        positionLabel.setBounds(24, 213, 133, 16);
        mainPanel.add(positionLabel);

        JLabel permissionsLabel = new JLabel("Права доступа");
        permissionsLabel.setBounds(432, 98, 126, 16);
        mainPanel.add(permissionsLabel);

        JLabel officeLabel = new JLabel("Офис");
        officeLabel.setBounds(14, 153, 76, 16);
        mainPanel.add(officeLabel);

        JLabel userNameLbl = new JLabel("Имя пользователя");
        userNameLbl.setBounds(15, 289, 142, 14);
        mainPanel.add(userNameLbl);

        JLabel passwordLabel = new JLabel("Пароль");
        passwordLabel.setBounds(22, 333, 102, 14);
        mainPanel.add(passwordLabel);

        JLabel salaryLabel = new JLabel("Зарплата");
        salaryLabel.setBounds(448, 176, 83, 16);
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

    private Employee getEmployee() {
        String fullName = fullNameField.getText();
        Date bornDate = bornDateField.getDate();
        Date hireDate = hireDateField.getDate();

        String login = userNameField.getText();
        String password = passwordField.getText();

        String mobilePhone = mobilePhoneField.getText();
        String eMail = emailField.getText();

        if (isEmptyFields(fullName, login, password)) {
            return null;
        }

        Position position = positions.get(positionComboBox.getSelectedIndex());
        Permission permission = permissions.get(permissionComboBox.getSelectedIndex());
        Office office = offices.get(officeComboBox.getSelectedIndex());

        int salaryValue = ((Double) salaryField.getValue()).intValue();

        Authentication authentication = new Authentication();
        authentication.setUserName(login);
        authentication.setPassword(password);

        DeliveryServiceDao.getInstance().addRecord(authentication);

        Salary salary = new Salary();
        salary.setBaseRate(salaryValue);
        salary.setRaisingFactor(1.0);

        DeliveryServiceDao.getInstance().addRecord(salary);

        Employee employee = new Employee();
        employee.setAuthentication(authentication);
        employee.setSalary(salary);
        employee.setPosition(position);
        employee.setPermission(permission);
        employee.setOffice(office);

        employee.setFullName(fullName);
        employee.setBirthday(bornDate);
        employee.setHireDate(hireDate);
        employee.setPhoneNumber(mobilePhone);
        employee.seteMail(eMail);

        return employee;
    }

    private boolean isEmptyFields(String... fields) {
        for (String field : fields) {
            if (field.trim().isEmpty()) {
                return true;

            }
        }
        return false;
    }

    private void createActionElements() {
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

        bornDateField = new JXDatePicker();
        bornDateField.setDate(new Date());
        bornDateField.setBounds(16, 60, 152, 38);
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
        positionComboBox.setBounds(14, 231, 649, 26);
        mainPanel.add(positionComboBox);

        permissionComboBox = new JComboBox<String>();
        permissionComboBox.setBounds(433, 116, 240, 26);
        mainPanel.add(permissionComboBox);

        officeComboBox = new JComboBox<String>();
        officeComboBox.setBounds(15, 171, 410, 26);
        mainPanel.add(officeComboBox);

        hireDateField = new JXDatePicker();
        hireDateField.setBounds(179, 60, 144, 38);
        hireDateField.setDate(new Date());
        mainPanel.add(hireDateField);

        userNameField = new JTextField();
        userNameField.setColumns(10);
        userNameField.setBounds(160, 287, 502, 28);
        mainPanel.add(userNameField);

        passwordField = new JPasswordField();
        passwordField.setColumns(10);
        passwordField.setBounds(160, 325, 502, 28);
        mainPanel.add(passwordField);

        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
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
        addButton.setBounds(216, 373, 220, 23);
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Employee employee = getEmployee();
                if (employee == null) {
                    JOptionPane.showMessageDialog(null, "Заполнены не все обязательные поля", "Error",
                            JOptionPane.PLAIN_MESSAGE);
                } else {
                    boolean isSuccessfully = DeliveryServiceDao.getInstance().addRecord(employee);
                    if (isSuccessfully) {
                        JOptionPane.showMessageDialog(null, Messages.EMPLOYEE_SUCCESSFULLY_ADDED.get(), "Message",
                                JOptionPane.PLAIN_MESSAGE);
                        log.info(Messages.EMPLOYEE_SUCCESSFULLY_ADDED.get());
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, Messages.ERROR_WHILE_ADD_RECORD.get(), "Error",
                                JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        });
        mainPanel.add(addButton);

    }

    private void fillComboBoxes() {
        positions = DeliveryServiceDao.getInstance().getAllRecord(Position.class);
        permissions = DeliveryServiceDao.getInstance().getAllRecord(Permission.class);
        offices = DeliveryServiceDao.getInstance().getAllRecord(Office.class);

        fillComboBox(positionComboBox, positions);
        fillComboBox(permissionComboBox, permissions);
        fillComboBox(officeComboBox, offices);
    }

    private void fillComboBox(JComboBox<String> target, List<? extends Describable> source) {
        for (Describable item : source) {
            target.addItem(item.getDescription());
        }
    }
}
