package by.bsuir.verkpavel.adb.ui.account;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
    private DateTimeFormatter dateMask = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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
                ArrayList<Deposit> deposits = DataProvider.getInstance().getAllDeposits();
                for (Deposit deposit : deposits) {
                    if (LocalDate.parse(deposit.startDate, dateMask).isEqual(LocalDate.now())) {
                        DataProvider.getInstance().addTransaction(
                                DataProvider.getInstance().getAccountByDeposit(deposit)[0],
                                DataProvider.getInstance().getFDBAccount(), deposit.depositSum);
                    }
                    if (LocalDate.now().getDayOfMonth() == LocalDate.now().lengthOfMonth()) {
                        DataProvider.getInstance().addTransaction(
                                DataProvider.getInstance().getFDBAccount(),
                                DataProvider.getInstance().getAccountByDeposit(deposit)[1],
                                deposit.depositSum * deposit.persent / 12);
                        if(deposit.depositType == 1){
                            DataProvider.getInstance().addTransaction(
                                    DataProvider.getInstance().getAccountByDeposit(deposit)[1],
                                    DataProvider.getInstance().getCashBoxAccount(),
                                    deposit.depositSum * deposit.persent / 12);
                        }
                    }
                    
                    if(LocalDate.parse(deposit.endDate, dateMask).isEqual(LocalDate.now())){
                        if(deposit.depositType==1){
                            DataProvider.getInstance().updateDepositEndDate(deposit, LocalDate.parse(deposit.endDate, dateMask).plusMonths(1).format(dateMask));
                        }
                        if(deposit.depositType==2){
                              DataProvider.getInstance().addTransaction(DataProvider.getInstance().getAccountByDeposit(deposit)[1], DataProvider.getInstance().getCashBoxAccount(), (ChronoUnit.MONTHS.between(LocalDate.parse(deposit.startDate, dateMask), LocalDate.parse(deposit.endDate, dateMask))/12*deposit.persent));
                              DataProvider.getInstance().addTransaction(DataProvider.getInstance().getAccountByDeposit(deposit)[0], DataProvider.getInstance().getCashBoxAccount(), deposit.depositSum);
                        }
                    }
                }
            }        });
        addButton.setBounds(141, 238, 151, 23);
        mainPanel.add(addButton);

        listModel.clear();
        for (Account account : accounts) {
            listModel.addElement(account.number);
        }
    }
}
