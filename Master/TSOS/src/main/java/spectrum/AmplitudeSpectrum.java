package spectrum;

import java.util.ArrayList;
import java.util.List;

public class AmplitudeSpectrum extends Spectrum {

    private List<Double> amplitudeSpectrum = new ArrayList<>();

    private List<Double> as;
    private List<Double> ac;

    public AmplitudeSpectrum(List<Double> as, List<Double> ac, int n) {
        super(n);
        this.as = as;
        this.ac = ac;
    }

    public AmplitudeSpectrum(List<Double> value, int n) {
        super(n);
        this.amplitudeSpectrum = value;
    }

    private void calculateData() {
        for (int i = 0; i < ac.size(); i++) {
            amplitudeSpectrum.add(Math.sqrt(Math.pow(ac.get(i), 2) + Math.pow(as.get(i), 2)));
        }
    }

    @Override
    public List<Double> getValues() {
        if (amplitudeSpectrum.isEmpty()) {
            calculateData();
        }
        return this.amplitudeSpectrum;
    }

}
