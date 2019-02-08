package signals;

import graph.DrawType;
import graph.LineChart;
import org.jfree.chart.ChartPanel;

import java.util.ArrayList;
import java.util.List;

public abstract class Drawable {
    private int N;
    private float F;
    public Drawable(int n, float f) {
        this.N = n;
        this.F = f;
    }

    public ChartPanel buildGraph(String title){
        LineChart lineChart = new LineChart(title);
        lineChart.addDataset(getValues(), 1,  1.0 / N);
        return lineChart.build(DrawType.LINE);
    }
    
    public abstract List<Double> getValues();

    public int getN() {
        return N;
    }

    public float getF() {return F;}

    public void setN(int n) {
        this.N = n;
    }
}
