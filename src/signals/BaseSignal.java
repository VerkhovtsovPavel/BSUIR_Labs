package signals;

import java.util.ArrayList;
import java.util.Random;

import graph.LineChart;

public abstract class BaseSignal {
    protected final double calculationStep = 0.01;
    protected int amplitude;
    protected int frequency;
    protected double initialPhase;
    protected int N;
    protected int n;

    public BaseSignal(int amplitude, int frequency, double initialPhase, int N) {
        this.amplitude = amplitude;
        this.frequency = frequency;
        this.initialPhase = initialPhase;
        this.N = N;
        this.n = new Random().nextInt(N);
    }
    
    public void buildGraph(int count, String title){
        LineChart lineChart = new LineChart(title);
        for (int i = 0; i < count; i++) {
            lineChart.addDataset(calculateData(i), i+1, calculationStep);
        }
        lineChart.draw();
    }

    protected abstract ArrayList<Double> calculateData(int i);
}
