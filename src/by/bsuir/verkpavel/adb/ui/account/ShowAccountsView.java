package by.bsuir.verkpavel.adb.ui.account;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import by.bsuir.verkpavel.adb.data.DataProvider;
import by.bsuir.verkpavel.adb.data.entity.Account;
import by.bsuir.verkpavel.adb.data.entity.Deposit;
import by.bsuir.verkpavel.adb.ui.MainView;

public class ShowAccountsView extends JFrame {
    private static final long serialVersionUID = 2883993883146596569L;
    private JPanel mainPanel;

    ArrayList<Account> accounts;

    private static void initialaze() {
        ShowAccountsView frame = new ShowAccountsView();
        frame.setSize(460, 310);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                MainView.create();
            }
        });
    }

    public static void create() {
        initialaze();
    }

    private ShowAccountsView() {
        setTitle("Счета");
        configureDefaultLayot();
    }

    private void configureDefaultLayot() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        mainPanel = new JPanel();
        setContentPane(mainPanel);
        mainPanel.setLayout(null);

        accounts = DataProvider.getInstance().getAllAccounts();

        final DefaultListModel<String> listModel = new DefaultListModel<>();
        final JList<String> list = new JList<String>(listModel);
        list.setBounds(10, 32, 422, 176);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (list.getSelectedIndex() != -1) {
                        ShowView.create(null/*
                                             * accounts.get(list.getSelectedIndex
                                             * ())
                                             */);
                        dispose();
                    }
                }
            }
        });
        mainPanel.add(list);

        JLabel usersLbl = new JLabel("Счета");
        usersLbl.setBounds(190, 6, 76, 14);
        mainPanel.add(usersLbl);

        JButton addButton = new JButton("Закрыть день");
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ArrayList<Deposit> deposits = DataProvider.getInstance().getAllDeposits();
                for (Deposit deposit : deposits) {
                    // TODO Implement check
                    if (deposit.startDate == null/* Today */) {
                        DataProvider.getInstance().addTransaction(
                                DataProvider.getInstance().getAccountByDeposit(deposit)[0],
                                DataProvider.getInstance().getFDBAccount(), deposit.depositSum);
                    }
                    // TODO Implement check and check deposit type
                    if (true/* Today last day of month */&& deposit.depositType == 1) {
                        DataProvider.getInstance().addTransaction(
                                DataProvider.getInstance().getFDBAccount(),
                                DataProvider.getInstance().getAccountByDeposit(deposit)[1],
                                deposit.depositSum * deposit.persent / 12);
                    }
                    
                    if(deposit.endDate == null/* Today */){
                        if(deposit.depositType==2){
                            
                        }
                    }
                }
                DataProvider.getInstance().addTransaction((Account) null, (Account) null, 1000);
            }

        });
        addButton.setBounds(141, 238, 151, 23);
        mainPanel.add(addButton);

        listModel.clear();

        listModel.addElement("10105478367837468");
        for (Account account : accounts) {
            listModel.addElement(account.number);
        }
    }
}
