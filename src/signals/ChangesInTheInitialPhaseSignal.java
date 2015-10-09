package signals;

import java.util.ArrayList;

public class ChangesInTheInitialPhaseSignal extends BaseSignal{


    public ChangesInTheInitialPhaseSignal(int amplitude, double initalPhase, int N) {
        super(amplitude, initalPhase, N);
    }

    @Override
    protected ArrayList<Double> calculateData(int number) {
        ArrayList<Double> values = new ArrayList<Double>();
        for (int n = 0; n < N; n ++) {
            values.add((float)amplitude
                    * 2 * Math.PI * n / N + (initialPhase + (float)number/6));
        }
        return values;
    }

}
