package graph;

import java.awt.Color;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class LineChart extends ApplicationFrame {
	private static final long serialVersionUID = 1359626195414020994L;
	private XYSeriesCollection dataset;

	public LineChart(final String title) {
		super(title);
		dataset = new XYSeriesCollection();
	}

	public void addDataset(ArrayList<Double> values, int number, double step) {

		XYSeries series = new XYSeries(number);
		for (int i = 0; i < values.size(); i++) {
			series.add((double) i * step, values.get(i));
		}

		dataset.addSeries(series);

	}

	public void draw(String type) {
		JFreeChart chart = null;

		if (type.equals("line")) {
			chart = ChartFactory.createXYLineChart(this.getTitle(), "X", "Y",
					dataset, PlotOrientation.VERTICAL, true, true, false);
		} else if (type.equals("bar")) {
			chart = ChartFactory.createXYBarChart(this.getTitle(), "X", false,
					"Y", dataset, PlotOrientation.VERTICAL, true, true, false);
		}

		chart.setBackgroundPaint(Color.white);

		final XYPlot plot = chart.getXYPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);

		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);

		this.pack();
		RefineryUtilities.centerFrameOnScreen(this);
		this.setVisible(true);

	}

}