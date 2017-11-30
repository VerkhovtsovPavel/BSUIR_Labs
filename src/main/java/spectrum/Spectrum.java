package spectrum;

import graph.DrawType;
import graph.LineChart;
import org.jfree.chart.ChartPanel;
import signals.Drawable;

public abstract class Spectrum extends Drawable{

    public Spectrum(int n) {
        super(n, 0.0f);
    }

    @Override
    public ChartPanel buildGraph(String title){
        LineChart lineChart = new LineChart(title);
        lineChart.addDataset(getValues(), 1, 1);
        return lineChart.build(DrawType.BAR);
    }


    public void drawGraph(String title) {
        LineChart lineChart = new LineChart(title);
        lineChart.addDataset(getValues(), 1, 1);
        lineChart.draw(DrawType.BAR);
    }
}
