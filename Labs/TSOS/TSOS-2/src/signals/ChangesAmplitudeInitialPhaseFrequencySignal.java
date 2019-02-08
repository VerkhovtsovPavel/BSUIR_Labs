package signals;

import graph.LineChart;

import java.util.ArrayList;

public class ChangesAmplitudeInitialPhaseFrequencySignal extends BaseSignal {

    private ArrayList<Double> values;
    private double mathExpertion = Float.NaN;

    public ChangesAmplitudeInitialPhaseFrequencySignal(int amplitude, double initilaPhase, int N) {
        super(amplitude, initilaPhase, N);
    }

    public void buildGraphOfSum(int count) {
        LineChart chartDrawer = new LineChart("Graph");
        ArrayList<ArrayList<Double>> charts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            charts.add(calculateData(i));
        }

        for (int i = 1; i < charts.size(); i++) {
            for (int j = 0; j < charts.get(i).size(); j++) {
                charts.get(0).set(j, charts.get(0).get(j) + charts.get(i).get(j));
            }
        }
        chartDrawer.addDataset(charts.get(0), 1, 1);
        values = charts.get(0);
        chartDrawer.draw();
    }

    @Override
    protected ArrayList<Double> calculateData(int number) {
        ArrayList<Double> values = new ArrayList<Double>();
        for (int n = 0; n < N; n++) {
            values.add((amplitude + number)
                    * Math.cos(2 * Math.PI * n * (number + 1) / N
                            + (initialPhase + (float) number / 6)));
        }
        return values;
    }
    
    public double buildDispersion(){
        ArrayList<Double> dispersionPoints = new ArrayList<>();
        double dispersionValue = 0;
        for(double value : values){
            double deviation = Math.pow(value-getMathExpection(), 2);
            dispersionPoints.add(deviation);
            dispersionValue+=deviation;
        }
        LineChart chartDrawer = new LineChart("Dispersion");

        chartDrawer.addDataset(dispersionPoints, 1, 1);
        chartDrawer.draw();
        return dispersionValue/values.size();
    }

    public double getMathExpection() {
        if (Double.isNaN(this.mathExpertion)) {
            double total = 0;
            for (double value : values) {
                total += value;
            }
            this.mathExpertion = total / values.size();
        }
        return this.mathExpertion;
    }
    
    public void buildRGraph(){
        ArrayList<Double> points = new ArrayList<Double>();
        for(int r=0; r< 10*N; r++){
            double point =0;
            for(int i=0; i<values.size(); i++){
                point+=values.get(i)*values.get((i+r)%values.size());
            }
            points.add(point/values.size());
        }
        LineChart chartDrawer = new LineChart("R");

        chartDrawer.addDataset(points, 1, 1);
        chartDrawer.draw();
    }
}
