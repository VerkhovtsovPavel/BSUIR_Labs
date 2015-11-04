package by.bsuir.verkpavel.adb.server.ui.credit;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import by.bsuir.verkpavel.adb.server.data.CreditProvider;
import by.bsuir.verkpavel.adb.server.data.entity.Credit;
import by.bsuir.verkpavel.adb.server.ui.ActionMode;
import by.bsuir.verkpavel.adb.server.ui.MainView;

public class ShowCreditsView extends JFrame {
    private static final long serialVersionUID = 2883993883146596569L;
    private JPanel mainPanel;

    ArrayList<Credit> credits;

    private static void initialaze() {
        ShowCreditsView frame = new ShowCreditsView();
        frame.setSize(460, 310);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setVisible(true);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                MainView.create();
            }
        });
    }

    public static void create() {
        initialaze();
    }

    private ShowCreditsView() {
        setTitle("Кредиты");
        configureDefaultLayot();
    }

    private void configureDefaultLayot() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        mainPanel = new JPanel();
        setContentPane(mainPanel);
        mainPanel.setLayout(null);

        credits = CreditProvider.getInstance().getAllCredits();

        final DefaultListModel<String> listModel = new DefaultListModel<>();
        final JList<String> list = new JList<String>(listModel);
        list.setBounds(10, 32, 422, 176);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (list.getSelectedIndex() != -1) {
                        ActionView.create(ActionMode.SHOW, credits.get(list.getSelectedIndex()));
                        dispose();
                    }
                }
            }
        });
        mainPanel.add(list);

        JLabel usersLbl = new JLabel("Кредиты");
        usersLbl.setBounds(190, 6, 76, 14);
        mainPanel.add(usersLbl);

        JButton addButton = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
        addButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                ActionView.create(ActionMode.ADD, null);
                dispose();
            }

        });
        addButton.setBounds(174, 238, 111, 23);
        mainPanel.add(addButton);

        listModel.clear();
        for (Credit credit : credits) {
            listModel.addElement(credit.contractNumber);
        }
    }
}
