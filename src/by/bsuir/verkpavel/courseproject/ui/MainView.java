package by.bsuir.verkpavel.courseproject.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import by.bsuir.verkpavel.courseproject.access.MenuCreator;
import by.bsuir.verkpavel.courseproject.dao.entity.Employee;
import by.bsuir.verkpavel.courseproject.ui.companetns.ImageLabel;
import java.awt.Color;

public class MainView extends JFrame {
    private static final long serialVersionUID = 2883993883146596569L;
    private JPanel mainPanel;
    
    private static JFrame self;

    private JMenuBar menuBar;

    public void showView() {
        self = this;
        this.setSize(620, 210);
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

    public MainView(Employee currentUser) {
        setBackground(Color.WHITE);
        setResizable(false);
        configureDefaultLayot();
        menuBar = MenuCreator.createPermissibleMenu(currentUser);
        setJMenuBar(menuBar);
    }

    private void configureDefaultLayot() {
        setTitle("Главная форма");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        setContentPane(mainPanel);
        mainPanel.setLayout(null);

        ImageLabel logoIcon = new ImageLabel("res/background.jpeg");
        logoIcon.setBounds(6, 6, 595, 154);
        mainPanel.add(logoIcon);
    }
    
    public static void closeForm(){
       if(self!=null){
           self.dispose();
       }
    }
}
