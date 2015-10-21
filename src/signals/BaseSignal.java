package signals;

import graph.LineChart;

import java.util.ArrayList;

public abstract class BaseSignal {
    protected int amplitude;
    protected double initialPhase;
    protected int N;

    public BaseSignal(int amplitude, double initialPhase, int N) {
        this.amplitude = amplitude;
        this.initialPhase = initialPhase;
        this.N = N;
    }
    
    public void buildGraph(int count, String title){
        LineChart lineChart = new LineChart(title);
        for (int i = 0; i < count; i++) {
            lineChart.addDataset(calculateData(i), i+1, 1);
        }
        lineChart.draw();
    }

    protected abstract ArrayList<Double> calculateData(int i);
}
