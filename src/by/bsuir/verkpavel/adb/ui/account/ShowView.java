package by.bsuir.verkpavel.adb.ui.account;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import by.bsuir.verkpavel.adb.data.entity.Account;

//TODO Create UI
public class ShowView extends JFrame {
    private static final long serialVersionUID = 2883993883146596569L;
    protected static Account currentAccount;

    private JList<Long> debitList;
    private JList<Long> creditList;
    
    protected JPanel mainPanel;

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
        setTitle("Счет");
        configureDefaultLayot();
    }

    private void configureDefaultLayot() throws ParseException {
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

    private void createElements() throws ParseException {
        createLabels();
        createActionElements();
    }

    private void createLabels() {
        JLabel debitLabel = new JLabel("Дебит");
        debitLabel.setBounds(107, 11, 46, 14);
        mainPanel.add(debitLabel);
        
        JLabel creditLabel = new JLabel("Кредит");
        creditLabel.setBounds(441, 11, 46, 14);
        mainPanel.add(creditLabel);
    }

    private void createActionElements() throws ParseException {
        debitList = new JList<Long>();
        debitList.setBounds(32, 287, 240, -250);
        mainPanel.add(debitList);
        
        creditList = new JList<Long>();
        creditList.setBounds(327, 287, 240, -250);
        mainPanel.add(creditList);
    }
}
