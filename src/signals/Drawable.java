package signals;

import graph.LineChart;

import java.util.ArrayList;

public abstract class Drawable {
    private int N;
    public Drawable(int n) {
        this.N =n;
    }

    public void buildGraph(String title){
        LineChart lineChart = new LineChart(title);
        lineChart.addDataset(calculateData(), 1, 1);
        lineChart.draw();
    }

    protected abstract ArrayList<Double> calculateData();

    public int getN() {
        return N;
    }
}
