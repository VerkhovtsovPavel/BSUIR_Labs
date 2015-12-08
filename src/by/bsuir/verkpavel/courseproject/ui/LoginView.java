package by.bsuir.verkpavel.courseproject.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.j256.ormlite.stmt.QueryBuilder;

import by.bsuir.verkpavel.courseproject.access.PasswordEncryptor;
import by.bsuir.verkpavel.courseproject.dao.DeliveryServiceDao;
import by.bsuir.verkpavel.courseproject.dao.entity.Authentication;
import by.bsuir.verkpavel.courseproject.dao.entity.Employee;
import by.bsuir.verkpavel.courseproject.resources.Messages;

public class LoginView extends JFrame {
    private static final long serialVersionUID = 2883993883146596569L;
    private JPanel mainPanel;
    private JTextField loginTextField;
    private JTextField passwordTextField;

    public void showView() {
        this.setSize(450, 180);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    public LoginView() {
        configureDefaultLayot();
    }

    private void configureDefaultLayot() {
        setResizable(false);
        setTitle("Вход в систему");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        mainPanel = new JPanel();
        setContentPane(mainPanel);
        mainPanel.setLayout(null);

        loginTextField = new JTextField();
        loginTextField.setBounds(179, 26, 255, 20);
        mainPanel.add(loginTextField);
        loginTextField.setColumns(10);

        JLabel loginLabel = new JLabel("Имя пользователя");
        loginLabel.setBounds(26, 29, 102, 14);
        mainPanel.add(loginLabel);

        JLabel passwordLabel = new JLabel("Пароль");
        passwordLabel.setBounds(26, 67, 102, 14);
        mainPanel.add(passwordLabel);

        passwordTextField = new JPasswordField();
        passwordTextField.setColumns(10);
        passwordTextField.setBounds(179, 64, 255, 20);
        mainPanel.add(passwordTextField);

        JButton clientsBtn = new JButton("Войти");
        clientsBtn.setBounds(159, 107, 111, 23);
        clientsBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                String login = loginTextField.getText();
                String passwordHash = PasswordEncryptor.encryptPassword(passwordTextField.getText());

                QueryBuilder<Authentication, Integer> statementBuilder = DeliveryServiceDao.getInstance()
                        .getQueryBuilderByClass(Authentication.class);

                try {
                    // TODO move to DAO
                    statementBuilder.where().eq("userName", login).and().eq("password", passwordHash);
                } catch (SQLException e) {
                }
                List<Authentication> authentications = DeliveryServiceDao.getInstance()
                        .getExeciteQuery(statementBuilder, Authentication.class);

                if (!authentications.isEmpty()) {
                    Employee employee = getEmployeeByAuth(authentications.get(0));
                    MainView mainView = new MainView(employee);
                    mainView.showView();
                } else {
                    JOptionPane.showMessageDialog(null, Messages.INVALID_USERNAME_AND_PASSWORD.get(), "Error",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }

            private Employee getEmployeeByAuth(Authentication authentication) {
                QueryBuilder<Employee, Integer> statementBuilder = DeliveryServiceDao.getInstance()
                        .getQueryBuilderByClass(Employee.class);
                try {
                    statementBuilder.where().idEq(authentication.getIdAuthentication());
                } catch (SQLException e) {
                }
                List<Employee> employees = DeliveryServiceDao.getInstance().getExeciteQuery(statementBuilder,
                        Employee.class);
                return employees.get(0);
            }
        });
        mainPanel.add(clientsBtn);
    }
}
