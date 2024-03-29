package by.bsuir.verkpavel.db.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import by.bsuir.verkpavel.db.dao.db.threadpool.DBTask;
import by.bsuir.verkpavel.db.dao.db.threadpool.DBThreadPool;
import by.bsuir.verkpavel.db.fileutils.FileWriter;
import by.bsuir.verkpavel.db.logic.RequestGenerator;

public class MainView extends JFrame {
    private static final long serialVersionUID = 2883993883146596569L;
    private static final String SAVED_DATA = "savedData.txt";
    private JPanel mainPanel;
    private JTextField connectionStringTextField;
    private JTextField userNameTextField;
    private JTextField passwordTextField;
    private JTextArea requestTextArea;

    public void showView() {
        this.setSize(460, 500);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        this.setVisible(true);

        fillFieldsFromFile();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    private void fillFieldsFromFile() {
        try {
            String fileData = FileWriter.read(SAVED_DATA);
            String[] fieldValues = fileData.split("~");
            connectionStringTextField.setText(fieldValues[0]);
            userNameTextField.setText(fieldValues[1]);
            passwordTextField.setText(fieldValues[2]);
            requestTextArea.setText(fieldValues[3]);
        } catch (Exception e) {

        }

    }

    public MainView() {
        configureDefaultLayot();
    }

    private void configureDefaultLayot() {
        setTitle("Генератор запросов");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        mainPanel = new JPanel();
        setContentPane(mainPanel);
        mainPanel.setLayout(null);

        final JCheckBox transactionCheckBox = new JCheckBox("Транзакция");
        transactionCheckBox.setFont(new Font("Dialog", Font.PLAIN, 11));
        transactionCheckBox.setBounds(14, 135, 85, 23);
        mainPanel.add(transactionCheckBox);

        final JSpinner countOfOperatorsSpinner = new JSpinner();
        countOfOperatorsSpinner.setModel(new SpinnerNumberModel(1, 1, 1000, 1));
        countOfOperatorsSpinner.setBounds(224, 136, 55, 20);
        mainPanel.add(countOfOperatorsSpinner);

        JLabel countOperators = new JLabel("Операторов в запросе");
        countOperators.setFont(new Font("Dialog", Font.PLAIN, 11));
        countOperators.setBounds(103, 139, 123, 14);
        mainPanel.add(countOperators);

        JLabel countOfThreadsLb = new JLabel("Кол-во потоков");
        countOfThreadsLb.setFont(new Font("Dialog", Font.PLAIN, 11));
        countOfThreadsLb.setBounds(289, 139, 94, 14);
        mainPanel.add(countOfThreadsLb);

        final JSpinner countOfThreadsSpinner = new JSpinner();
        countOfThreadsSpinner.setModel(new SpinnerNumberModel(1, 1, 10, 1));
        countOfThreadsSpinner.setBounds(382, 137, 34, 20);
        mainPanel.add(countOfThreadsSpinner);

        requestTextArea = new JTextArea();
        requestTextArea.setLineWrap(true);
        requestTextArea.setWrapStyleWord(true);
        requestTextArea.setColumns(1);
        requestTextArea.setBounds(14, 177, 418, 187);

        JLabel label = new JLabel("Кол-во запросов");
        label.setFont(new Font("Dialog", Font.PLAIN, 11));
        label.setBounds(27, 406, 108, 14);
        mainPanel.add(label);

        final JSpinner countOfRequestSpinner = new JSpinner();
        countOfRequestSpinner.setModel(new SpinnerNumberModel(1, 1, 1000, 1));
        countOfRequestSpinner.setBounds(148, 403, 78, 20);
        mainPanel.add(countOfRequestSpinner);

        JButton button = new JButton("Запустить");
        button.setBounds(265, 403, 129, 23);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String connectionString = connectionStringTextField.getText();
                String userName = userNameTextField.getText();
                String password = passwordTextField.getText();
                String request = requestTextArea.getText();

                saveDataInFile(connectionString, userName, password, request);

                int countOperatorsInRequest = (int) countOfOperatorsSpinner.getValue();
                RequestGenerator requestGenerator = new RequestGenerator(countOperatorsInRequest, request);

                int countOfThreads = (int) countOfThreadsSpinner.getValue();

                DBThreadPool dbThreadPool = new DBThreadPool(countOfThreads, connectionString, userName, password);
                if (dbThreadPool.isSuccessfullyCreated) {
                    dbThreadPool.startTreads();
                    int countOfRequest = (int) countOfRequestSpinner.getValue();
                    boolean isTransaction = transactionCheckBox.isSelected();
                    for (int i = 0; i < countOfRequest; i++) {
                        dbThreadPool.addTask(new DBTask(requestGenerator.getParametrazedQuery(), isTransaction, i));
                    }

                }
            }

            private void saveDataInFile(String connectionString, String userName, String password, String request) {
                // WARNING Data not encrypted
                FileWriter.write(SAVED_DATA,
                        String.format("%s~%s~%s~%s", connectionString, userName, password, request));
            }
        });
        mainPanel.add(button);

        connectionStringTextField = new JTextField();
        connectionStringTextField.setBounds(140, 11, 276, 20);
        connectionStringTextField.setFont(new Font("Dialog", Font.PLAIN, 11));
        mainPanel.add(connectionStringTextField);
        connectionStringTextField.setColumns(10);

        JLabel connectionStringLabel = new JLabel("Строка подключения ");
        connectionStringLabel.setBounds(14, 14, 121, 14);
        connectionStringLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
        mainPanel.add(connectionStringLabel);

        userNameTextField = new JTextField();
        userNameTextField.setColumns(10);
        userNameTextField.setFont(new Font("Dialog", Font.PLAIN, 11));
        userNameTextField.setBounds(140, 42, 276, 20);
        mainPanel.add(userNameTextField);

        JLabel userNameLabel = new JLabel("Имя пользователя");
        userNameLabel.setBounds(14, 45, 121, 14);
        userNameLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
        mainPanel.add(userNameLabel);

        passwordTextField = new JPasswordField();
        passwordTextField.setColumns(10);
        passwordTextField.setFont(new Font("Dialog", Font.PLAIN, 11));
        passwordTextField.setBounds(140, 73, 276, 20);
        mainPanel.add(passwordTextField);

        JLabel passwordLabel = new JLabel("Пароль");
        passwordLabel.setBounds(14, 76, 121, 14);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
        mainPanel.add(passwordLabel);

        JScrollPane scrollPane = new JScrollPane(requestTextArea);
        scrollPane.setBounds(14, 177, 418, 187);
        mainPanel.add(scrollPane);
    }
}
