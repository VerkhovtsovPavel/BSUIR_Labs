package by.bsuir.verkpavel.adb.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class ShowUsersView extends JFrame {
    private static final long serialVersionUID = 2883993883146596569L;
    private JPanel mainPanel;

    private static void initialaze() {
        AddView frame = new AddView();
        frame.setSize(490, 420);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setVisible(true);
    }

    public static void create() {
        initialaze();
    }

    public ShowUsersView() {
        configureDefaultLayot();
    }

    private void configureDefaultLayot() {
        setResizable(false);
        setTitle("\u0414\u043E\u0431\u0430\u0432\u043B\u0435\u043D\u0438\u0435 \u043A\u043B\u0438\u0435\u043D\u0442\u0430");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        mainPanel = new JPanel();
        setContentPane(mainPanel);
        mainPanel.setLayout(null);

        JButton editBtn = new JButton("\u0420\u0435\u0434\u0430\u043A\u0442\u0438\u0440\u043E\u0432\u0430\u0442\u044C");
        editBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

        });
        editBtn.setBounds(25, 238, 111, 23);
        mainPanel.add(editBtn);

        JLabel usersLbl = new JLabel("\u041A\u043B\u0438\u0435\u043D\u0442\u044B");
        usersLbl.setBounds(188, 34, 66, 14);
        mainPanel.add(usersLbl);
        
        JList<String> list = new JList<String>();
        list.setBounds(10, 59, 422, 149);
        mainPanel.add(list);
        
        JButton deleteBtn = new JButton("\u0423\u0434\u0430\u043B\u0438\u0442\u044C");
        deleteBtn.setBounds(321, 238, 111, 23);
        mainPanel.add(deleteBtn);
    } 
}

