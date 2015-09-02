package by.bsiur.verkpavel.saimmod.graphics.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class HistogramWindow {

    private JFrame frame;

    public static void create(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HistogramWindow window = new HistogramWindow();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    private HistogramWindow() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
