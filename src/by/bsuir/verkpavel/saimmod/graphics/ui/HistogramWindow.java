package by.bsuir.verkpavel.saimmod.graphics.ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class HistogramWindow extends JPanel {
    private static final long serialVersionUID = 2722283204771345956L;
    private static final int screenSize = 600;
    private static final int offsetToBottom = 50;

    private float[] sectionSizes;

    public static void create(float[] sectionSizes) {
        JFrame mainFrame = new JFrame("Histogram");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setContentPane(new HistogramWindow(sectionSizes));
        mainFrame.setSize(screenSize, screenSize);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
        mainFrame.setBackground(Color.white);
    }

    private HistogramWindow(float[] sectionSizes) {
        this.sectionSizes = sectionSizes;
        this.setOpaque(true);
    }

    public void paint(final Graphics g) {
        int offset = screenSize / sectionSizes.length;
        offset-= 20/sectionSizes.length;
        scalingSection();
        for (int i = 0; i < sectionSizes.length; i++) {
            g.drawRect(offset * i, (int) (screenSize - sectionSizes[i] - offsetToBottom), offset,
                    (int)sectionSizes[i]);
        }
    }
    
    private void scalingSection() {
        for (int i = 0; i < sectionSizes.length; i++) {

            sectionSizes[i] *= (screenSize-offsetToBottom);
        }

    }

}
