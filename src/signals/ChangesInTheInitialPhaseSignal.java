package signals;

import java.util.ArrayList;

public class ChangesInTheInitialPhaseSignal extends BaseSignal{


    public ChangesInTheInitialPhaseSignal(int amplitude, int frequency, double initalPhase, int N) {
        super(amplitude, frequency, initalPhase, N);
    }

    @Override
    protected ArrayList<Double> calculateData(int number) {
        ArrayList<Double> values = new ArrayList<Double>();
        for (double i = 0; i < 10; i += calculationStep) {
            values.add(amplitude
                    * Math.cos(2 * Math.PI * n *i * frequency / N + (initialPhase + number * Math.PI/6)));
        }
        return values;
    }

}
