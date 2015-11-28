package by.bsuir.verkpavel.courseproject.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class LoginView extends JFrame {
    private static final long serialVersionUID = 2883993883146596569L;
    private JPanel mainPanel;
    private JTextField loginTextField;
    private JTextField passwordTextField;

    public void showView() {
        this.setSize(332, 204);
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

        JButton clientsBtn = new JButton("Войти");
        clientsBtn.setBounds(163, 213, 111, 23);
        clientsBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
        mainPanel.add(clientsBtn);
        
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
    }
}
