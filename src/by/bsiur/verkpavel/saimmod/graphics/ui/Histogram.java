package by.bsiur.verkpavel.saimmod.graphics.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;

public class Histogram extends JFrame {

    private static final long serialVersionUID = 1L;

    public Histogram(double[] a, int n, double max, double min) {
        JFreeChart chart = createChart(a, n, max, min);

        ChartPanel cpanel = new ChartPanel(chart);
        getContentPane().add(cpanel, BorderLayout.CENTER);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(10, 10, 500, 500);
        this.setTitle("Histogram");
        this.setVisible(true);
    }

    private JFreeChart createChart(double[] values, int sectionCount, double min, double max) {

        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("Values", values, sectionCount, min, max);
        dataset.setType(HistogramType.RELATIVE_FREQUENCY);

        JFreeChart chart = ChartFactory.createHistogram("Histogram", null, null, dataset,
                PlotOrientation.VERTICAL, true, false, false);
        chart.setBackgroundPaint(new Color(230, 230, 230));
        XYPlot xyplot = (XYPlot) chart.getPlot();
        xyplot.setForegroundAlpha(0.7F);
        xyplot.setBackgroundPaint(Color.WHITE);
        xyplot.setDomainGridlinePaint(new Color(150, 150, 150));
        xyplot.setRangeGridlinePaint(new Color(150, 150, 150));
        XYBarRenderer xybarrenderer = (XYBarRenderer) xyplot.getRenderer();
        xybarrenderer.setShadowVisible(false);
        xybarrenderer.setBarPainter(new StandardXYBarPainter());
        return chart;
    }

}
