package signals;

import graph.LineChart;

import java.util.ArrayList;

public class ChangesAmplitudeInitialPhaseFrequencySignal extends BaseSignal {

    public ChangesAmplitudeInitialPhaseFrequencySignal(int amplitude, int frequency, double initilaPhase, int N) {
        super(amplitude, frequency, initilaPhase, N);
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
        chartDrawer.addDataset(charts.get(0), 1, calculationStep);
        chartDrawer.draw();
    }

    @Override
    protected ArrayList<Double> calculateData(int number) {
        ArrayList<Double> values = new ArrayList<Double>();
        for (double i = 0; i < 10; i += calculationStep) {
            values.add((amplitude + number)
                    * Math.cos(2 * Math.PI * n *i * (frequency - number) / N
                            + (initialPhase + number * Math.PI / 6)));
        }
        return values;
    }
}
