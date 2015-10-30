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
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import by.bsuir.verkpavel.adb.data.AccountProvider;
import by.bsuir.verkpavel.adb.data.entity.Account;
import by.bsuir.verkpavel.adb.logic.AbstractDayCloser;
import by.bsuir.verkpavel.adb.logic.deposit.DepositDayCloser;
import by.bsuir.verkpavel.adb.ui.MainView;
//TODO Create secret procedure of closed month

//Runtime.getRuntime().exec("cmd /C date " + strDateToSet); Win
//date +%Y%m%d -s "20081128" Linux

public class ShowAccountsView extends JFrame {
    private static final long serialVersionUID = 2883993883146596569L;
    private static LocalDate lastClosedDate = LocalDate.MIN;
    private JPanel mainPanel;

    ArrayList<Account> accounts;
    ArrayList<AbstractDayCloser> dayClosers = new ArrayList<>();

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
        
        dayClosers.add(new DepositDayCloser());
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
                    for(AbstractDayCloser dayCloser : dayClosers){
                        dayCloser.closeDay();
                    }
                    lastClosedDate = LocalDate.now();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Текущий день предшествует либо равен последнему закрытому дню",
                            "Error", JOptionPane.PLAIN_MESSAGE);
                }
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
