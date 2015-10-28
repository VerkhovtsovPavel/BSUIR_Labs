package by.bsuir.verkpavel.adb.ui.account;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import by.bsuir.verkpavel.adb.data.AccountProvider;
import by.bsuir.verkpavel.adb.data.DepositProvider;
import by.bsuir.verkpavel.adb.data.entity.Account;
import by.bsuir.verkpavel.adb.data.entity.Deposit;
import by.bsuir.verkpavel.adb.data.entity.TransactionsInfo;
import by.bsuir.verkpavel.adb.ui.MainView;

public class ShowAccountsView extends JFrame {
    private static final long serialVersionUID = 2883993883146596569L;
    private DateTimeFormatter dateMask = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static LocalDate lastClosedDate = LocalDate.MIN;
    private JPanel mainPanel;

    ArrayList<Account> accounts;

    // MAYBE Use enum to deposit types
    private static void initialaze() {
        ShowAccountsView frame = new ShowAccountsView();
        frame.setSize(460, 310);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setVisible(true);

        lastClosedDate = getLastClosedDate();

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                saveLastClosedDate();
                MainView.create();
            }

            private void saveLastClosedDate() {
                try {
                    FileOutputStream fos = new FileOutputStream("lastClosedDayDate.out");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(lastClosedDate);
                    oos.flush();
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static LocalDate getLastClosedDate() {
        LocalDate ld = null;
        try {
            FileInputStream fis = new FileInputStream("lastClosedDayDate.out");
            ObjectInputStream oin = new ObjectInputStream(fis);
            ld = (LocalDate) oin.readObject();
            oin.close();
        } catch (IOException | ClassNotFoundException e1) {
        }

        if (ld != null) {
            return ld;
        }
        return lastClosedDate;
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

        accounts = AccountProvider.getInstance().getAllAccounts();

        final DefaultListModel<String> listModel = new DefaultListModel<>();
        final JList<String> list = new JList<String>(listModel);
        list.setBounds(10, 32, 422, 176);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (list.getSelectedIndex() != -1) {
                        ShowView.create(accounts.get(list.getSelectedIndex()));
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
                if (LocalDate.now().isAfter(lastClosedDate)) {
                    ArrayList<Deposit> deposits = DepositProvider.getInstance().getAllActiveDeposits();
                    for (Deposit deposit : deposits) {
                        AccountProvider.getInstance().addTransaction(
                                AccountProvider.getInstance().getFDBAccount(),
                                AccountProvider.getInstance().getAccountByDeposit(deposit)[1],
                                deposit.depositSum * deposit.persent / 360, deposit.currency);

                        if (LocalDate.parse(deposit.startDate, dateMask).isEqual(LocalDate.now())) {
                            AccountProvider.getInstance().addTransaction(
                                    AccountProvider.getInstance().getAccountByDeposit(deposit)[0],
                                    AccountProvider.getInstance().getFDBAccount(), deposit.depositSum,
                                    deposit.currency);
                        }
                        if (LocalDate.now().getDayOfMonth() == LocalDate.now().lengthOfMonth()) {
                            if (deposit.depositType == 2) {
                                AccountProvider.getInstance().addMonoTransaction(
                                        AccountProvider.getInstance().getAccountByDeposit(deposit)[1],
                                        AccountProvider.getInstance().getCashBoxAccount(),
                                        -getPersentsSum(deposit), deposit.currency);
                            }
                        }

                        if (LocalDate.parse(deposit.endDate, dateMask).isEqual(LocalDate.now())) {
                            if (deposit.depositType == 2) {
                                DepositProvider.getInstance().updateDepositEndDate(
                                        deposit,
                                        LocalDate.parse(deposit.endDate, dateMask).plusMonths(1)
                                                .format(dateMask));
                                AccountProvider.getInstance().addMonoTransaction(
                                        AccountProvider.getInstance().getAccountByDeposit(deposit)[1],
                                        AccountProvider.getInstance().getCashBoxAccount(),
                                        -getPersentsSum(deposit), deposit.currency);
                            }
                            if (deposit.depositType == 1) {
                                AccountProvider.getInstance().addMonoTransaction(
                                        AccountProvider.getInstance().getAccountByDeposit(deposit)[1],
                                        AccountProvider.getInstance().getCashBoxAccount(),
                                        -getPersentsSum(deposit), deposit.currency);

                                AccountProvider.getInstance().addTransaction(
                                        AccountProvider.getInstance().getFDBAccount(),
                                        AccountProvider.getInstance().getAccountByDeposit(deposit)[0],
                                        deposit.depositSum, deposit.currency);

                                AccountProvider.getInstance().addMonoTransaction(
                                        AccountProvider.getInstance().getAccountByDeposit(deposit)[0],
                                        AccountProvider.getInstance().getCashBoxAccount(),
                                        -deposit.depositSum, deposit.currency);
                                
                                DepositProvider.getInstance().disableDeposit(deposit);
                            }
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Текущий день (" + LocalDate.now()
                            + ") успешно закрыт", "Error", JOptionPane.PLAIN_MESSAGE);
                    lastClosedDate = LocalDate.now();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Текущий день предшествует либо равен последнему закрытому дню",
                            "Error", JOptionPane.PLAIN_MESSAGE);
                }
            }

            private double getPersentsSum(Deposit deposit) {
                List<TransactionsInfo> transactions = AccountProvider.getInstance()
                        .getTransactionsByAccount(
                                AccountProvider.getInstance().getAccountByDeposit(deposit)[1]);
                double totalPersentsSum = 0;
                for (TransactionsInfo transactionsInfo : transactions) {
                    totalPersentsSum += transactionsInfo.sum;
                }
                return totalPersentsSum;
            }
        });
        addButton.setBounds(141, 238, 151, 23);
        mainPanel.add(addButton);

        listModel.clear();
        for (Account account : accounts) {
            listModel.addElement(account.number);
        }
    }
}
