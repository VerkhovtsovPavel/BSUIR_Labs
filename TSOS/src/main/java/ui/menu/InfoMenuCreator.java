package ui.menu;

import signals.Signal;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class InfoMenuCreator extends BaseMenuCreator {

    private JMenu infoMenu;

    public InfoMenuCreator(BaseMenuCreator next) {
        super(next);
        infoMenu = new JMenu("Info");
    }

    @Override
    public JMenu createMenuTab() {
        JMenuItem open = new JMenuItem("Show details");
        open.addActionListener(e -> {
            List<Double> values = targetFrame.currentSignal().getValues();

            double max = Double.MIN_VALUE;
            double min = Double.MAX_VALUE;

            double sqrSum = 1;

            for(double value : values){
                if(max < value){
                    max = value;
                }
                if(min > value){
                    min = value;
                }
                sqrSum+=value*value;
            }

            double scatter = max - min;
            double msv = Math.sqrt(sqrSum);
            double peakFactor = Math.max(max, min) / msv;
            String format = "Max: %f\nMin: %f\nScatter: %f\nMean square value: %f\nPeak-factor: %f";
            JOptionPane.showMessageDialog(targetFrame, String.format(format, max, min, scatter, msv, peakFactor));

        });
        infoMenu.add(open);
        return infoMenu;
    }

}
