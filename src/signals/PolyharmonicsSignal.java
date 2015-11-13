package signals;

import java.util.ArrayList;

import random.SignalRandomParameters;

public class PolyharmonicsSignal extends Drawable {

    private ArrayList<SimpleSignal> signalHarmonics;

    public PolyharmonicsSignal(ArrayList<SimpleSignal> harmonics, int N) {
        super(N);
        this.signalHarmonics = harmonics;
    }

    public PolyharmonicsSignal(int N) {
        super(N);
        this.signalHarmonics = createHarmonics(30);
    }

    private ArrayList<SimpleSignal> createHarmonics(int count) {
        ArrayList<SimpleSignal> harmonics = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            harmonics.add(new SimpleSignal(SignalRandomParameters.getRandomAmplitude(), SignalRandomParameters
                    .getRandomInitialPhase(), i+1, getN()));
        }
        return harmonics;
    }

    @Override
    protected ArrayList<Double> calculateData() {
        ArrayList<Double> values = signalHarmonics.get(0).calculateData();
        for (int i = 1; i < signalHarmonics.size(); i++) {
            companeSignals(values, signalHarmonics.get(i).calculateData());
        }
        return values;
    }

    private void companeSignals(ArrayList<Double> values, ArrayList<Double> calculateData) {
        for (int i = 0; i < calculateData.size(); i++) {
            values.set(i, values.get(i) + calculateData.get(i));
        }
    }
}
