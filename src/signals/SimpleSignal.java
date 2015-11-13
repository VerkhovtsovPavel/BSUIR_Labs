package signals;

import java.util.ArrayList;

public class SimpleSignal extends Drawable{
    private int amplitude;
    private double initialPhase;
    private double frequency;

    public SimpleSignal(int amplitude, double initialPhase, double frequency, int N) {
        super(N);
        this.amplitude = amplitude;
        this.initialPhase = initialPhase;
        this.frequency = frequency;
    }

    @Override
    protected ArrayList<Double> calculateData() {
        ArrayList<Double> values = new ArrayList<Double>();
        for (int i = 0; i < getN(); i ++) {
            values.add(amplitude*Math.cos(
                    2 * Math.PI * i * frequency / getN() - initialPhase));
        }
        return values;
    }

}
