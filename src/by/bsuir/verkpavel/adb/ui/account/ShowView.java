package by.bsuir.verkpavel.adb.ui.account;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import by.bsuir.verkpavel.adb.data.DataProvider;
import by.bsuir.verkpavel.adb.data.entity.Account;
import by.bsuir.verkpavel.adb.data.entity.Account.AccountType;

public class ShowView extends JFrame {
    private static final long serialVersionUID = 2883993883146596569L;
    protected static Account currentAccount;

    private JList<Double> debitList;
    private JList<Double> creditList;

    private DefaultListModel<Double> debitListModel;
    private DefaultListModel<Double> creditListModel;

    protected JPanel mainPanel;

    private static void initialaze(ShowView actionView) {
        ShowView frame = null;
        frame = actionView;
        frame.setSize(600, 350);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
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
        debitListModel = new DefaultListModel<Double>();
        ;
        creditListModel = new DefaultListModel<Double>();
        creditList = new JList<Double>(creditListModel);
        creditList.setBounds(327, 37, 240, 250);
        mainPanel.add(creditList);
        debitList = new JList<Double>(debitListModel);
        debitList.setBounds(32, 37, 240, 250);
        mainPanel.add(debitList);

        fillListsByAccount();
    }

    private void fillListsByAccount() {
        ArrayList<Double> transactionsSum = DataProvider.getInstance().getTransatcionsByAccount(
                currentAccount);
        for (double sum : transactionsSum) {
            if (currentAccount.type == AccountType.ACTIVE) {
                if (sum < 0) {
                    creditListModel.addElement(sum);
                } else {
                    debitListModel.addElement(sum);
                }
            } else {
                if (sum > 0) {
                    creditListModel.addElement(sum);
                } else {
                    debitListModel.addElement(sum);
                }
            }
        }
    }
}
