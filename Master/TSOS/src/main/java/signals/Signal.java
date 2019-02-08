package signals;

import spectrum.AmplitudeSpectrum;
import spectrum.PhaseSpectrum;
import util.LUT;

import java.util.ArrayList;
import java.util.List;

public class Signal extends Drawable{

    private List<Double> values = new ArrayList<>();
    private AmplitudeSpectrum amplitudeSpec;
    private PhaseSpectrum phaseSpec;
    private List<Double> ac;
    private List<Double> as;

    public Signal(List<Double> value, int n, float f){
        super(n, f);
        this.values = value;
    }

    public Signal(AmplitudeSpectrum amplitudeSpec, PhaseSpectrum phaseSpec, int n, float f) {
        super(n, f);
        this.amplitudeSpec = amplitudeSpec;
        this.phaseSpec = phaseSpec;
        for (int i = 0; i < getN(); i++) {
            values.add(calculateSignalCompanent(i));
        }
    }

    private Double calculateSignalCompanent(int i) {
        double value = 0;
        float step = getN() / getF();
        int max = Math.min((int)step, values.size());
        for(int j=0; j<max/2-1; j++){
            value+=amplitudeSpec.getValues().get(j)* LUT.cosLut(2*Math.PI*j*i/step - phaseSpec.getValues().get(j));
        }
        return value;
    }

    private void calculateAcAndAs() {
        ac = new ArrayList<>();
        as = new ArrayList<>();
        for (int j = 0; j < getN() / 2 - 1; j++) {
            ac.add(calculateAcj(j));
            as.add(calculateAsj(j));
        }
    }

    private double calculateAsj(int j) {
        double sum = 0;
        float step = getN() / getF();
        int max = Math.min((int)step, values.size());
        for (int i = 0; i < max; i++) {
            sum += values.get(i) * LUT.sinLut(2 * Math.PI * j * i / step);
        }
        return sum * 2 / step;
    }

    private double calculateAcj(int j) {
        double sum = 0;
        float step = getN() / getF();
        int max = Math.min((int)step, values.size());
        for (int i = 0; i < max; i++) {
            sum += values.get(i) * LUT.cosLut(2 * Math.PI * j * i / step);
        }
        return sum * 2 / step;
    }

    public AmplitudeSpectrum getAmplitudeSpec(){
        if(amplitudeSpec==null){
            if(ac == null){
                calculateAcAndAs();
            }
            amplitudeSpec = new AmplitudeSpectrum(as, ac, getN());
        }
        return amplitudeSpec;
    }

    public PhaseSpectrum getPhaseSpec(){
        if(phaseSpec==null){
            if(ac == null){
                calculateAcAndAs();
            }
            phaseSpec = new PhaseSpectrum(as, ac, getN());
        }
        return phaseSpec;
    }

    @Override
    public List<Double> getValues() {
        return this.values;
    }
}
