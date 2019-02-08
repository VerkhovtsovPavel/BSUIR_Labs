package ui;

import graph.LineChart;
import signals.Signal;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class MainView extends JFrame {
    private static final long serialVersionUID = 2883993883146596569L;
    private Signal currentSignal;

    public void showView() {
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

    public MainView(Signal currentSignal) {
        this.currentSignal = currentSignal;
        setBackground(Color.WHITE);
        configureDefaultLayot();
        if(currentSignal!=null) {
            setContentPane(currentSignal.buildGraph(""));
        }
        setJMenuBar(MenuCreator.createMenu(this));
    }

    private void configureDefaultLayot() {
        setTitle("Main form");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        setContentPane(mainPanel);
        mainPanel.setLayout(null);
    }

    public void closeForm() {
            this.dispose();
    }

    public void addSignal(List<Double> values, int n, float f) {
        if(currentSignal==null) {
            currentSignal = new Signal(values, n, f);
            setContentPane(currentSignal.buildGraph(""));
            setVisible(true);
        }
        else {
            new MainView(new Signal(values, n, f)).showView();
        }
    }

    public Signal currentSignal() {
        return currentSignal;
    }
}
