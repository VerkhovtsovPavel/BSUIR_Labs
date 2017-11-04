package ui;


import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;


public class MainView extends JFrame {
    private static final long serialVersionUID = 2883993883146596569L;
    private JPanel mainPanel;

    private static JFrame self;

    private JMenuBar menuBar;

    public void showView() {
        self = this;
        this.setSize(650, 220);
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

    public MainView() {
        setBackground(Color.WHITE);
        setResizable(false);
        configureDefaultLayot();
        menuBar = MenuCreator.createPermissibleMenu();
        setJMenuBar(menuBar);
    }

    private void configureDefaultLayot() {
        setTitle("Главная форма");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        setContentPane(mainPanel);
        mainPanel.setLayout(null);
    }

    public static void closeForm() {
        if (self != null) {
            self.dispose();
        }
    }

    public String selectFile() {
        FileDialog chooser = new FileDialog(this, "Use a .png or .jpg extension", FileDialog.LOAD);
        chooser.setVisible(true);
        return chooser.getDirectory() + chooser.getFile();
    }
}
