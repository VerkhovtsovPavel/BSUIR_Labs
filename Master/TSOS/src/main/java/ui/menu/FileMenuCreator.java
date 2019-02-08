package ui.menu;

import graph.DrawType;
import graph.LineChart;
import util.FileUtil;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

public class FileMenuCreator extends BaseMenuCreator {

    private int n;

    private JMenu personalMenu;

    public FileMenuCreator(BaseMenuCreator next) {
        super(next);
        personalMenu = new JMenu("File");
    }

    @Override
    public JMenu createMenuTab() {
        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(e -> {
            String filename = selectFile();
            parseFile(filename);
        });
        JMenuItem close = new JMenuItem("Close");
        close.addActionListener(e -> {
            targetFrame.closeForm();
        });
        personalMenu.add(open);
        personalMenu.add(close);
        return personalMenu;
    }

    private String selectFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        int result = chooser.showOpenDialog(targetFrame);
        File file = null;
        if (result == JFileChooser.APPROVE_OPTION)
            file = chooser.getSelectedFile();
        return file.getAbsolutePath();
    }

    private void parseFile(String filepath) {
        if (filepath.split("\\.")[1].equalsIgnoreCase("txt")) {
            String fileContent = FileUtil.fileToString(filepath);
            String[] binFilesPaths = fileContent.split("[\r\n]+");
            String dir = new File(filepath).getParent()+"/";
            LineChart lineChart = new LineChart("Signals");
            int result = JOptionPane.showConfirmDialog(targetFrame, "Use separate views?", "Type of view", JOptionPane.YES_NO_OPTION);
            for (int i=0; i< binFilesPaths.length; i++) {
                String binPath = binFilesPaths[i];
                lineChart.addDataset(parseBin(dir + binPath, result), i+1, 1.0/n);
            }
            if (result == 1) {
                lineChart.draw(DrawType.LINE);
            }
        } else {
            parseBin(filepath);
        }
    }

    private ArrayList<Double> parseBin(String path, int isDisplay) {
        byte[] fourBytes = new byte[4];
        File file = new File(path);
        ArrayList<Double> values = new ArrayList<Double>();
        n = 0;
        float f = 0.0f;
        try (FileInputStream in = new FileInputStream(file)) {
            in.skip(8);
            in.read(fourBytes);
            ByteBuffer bb = ByteBuffer.wrap(fourBytes);
            bb.order(ByteOrder.LITTLE_ENDIAN);
            n = bb.getInt();
            in.skip(8);
            in.read(fourBytes);
            ByteBuffer ff = ByteBuffer.wrap(fourBytes);
            ff.order(ByteOrder.LITTLE_ENDIAN);
            f = ff.getFloat();
            in.skip(28);
            while (in.read(fourBytes) != -1) {
                bb = ByteBuffer.wrap(fourBytes);
                bb.order(ByteOrder.LITTLE_ENDIAN);
                float v = bb.getFloat();
                values.add((double) v);
            }
        } catch (Exception ignored) {
        }
        if(isDisplay==0)
            targetFrame.addSignal(values, n, f);
        return values;
    }

    private ArrayList<Double> parseBin(String path) {
        return parseBin(path, 0);
    }
}
