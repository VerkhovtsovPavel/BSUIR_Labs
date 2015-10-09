package signals;

import graph.LineChart;

import java.util.ArrayList;

public class ChangesAmplitudeInitialPhaseFrequencySignal extends BaseSignal {

    public ChangesAmplitudeInitialPhaseFrequencySignal(int amplitude, double initilaPhase, int N) {
        super(amplitude, initilaPhase, N);
    }

    public void buildGraphOfSum(int count) {
        LineChart chartDrawer = new LineChart("Part 2 (Sum)");
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
        chartDrawer.draw();
    }

    @Override
    protected ArrayList<Double> calculateData(int number) {
        ArrayList<Double> values = new ArrayList<Double>();
        for (int n = 0; n < N; n ++) {
            values.add((amplitude + number)
                    * Math.cos(2 * Math.PI * n * (number+1)  / N
                            + (initialPhase + (float)number/6)));
        }
        return values;
    }
}
