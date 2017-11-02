package signals;

import spectrum.AmplitudeSpectrum;
import spectrum.PhaseSpectrum;

import java.util.ArrayList;

public class SimpleSignal extends Drawable {
	private int amplitude;
	private double initialPhase;
	private double frequency;

	private ArrayList<Double> values;
	private AmplitudeSpectrum amplitudeSpec;
	private PhaseSpectrum phaseSpec;

	public SimpleSignal(int amplitude, double initialPhase, double frequency,
			int N) {
		super(N);
		this.amplitude = amplitude;
		this.initialPhase = initialPhase;
		this.frequency = frequency;
	}

	public SimpleSignal(AmplitudeSpectrum amplitudeSpec,
			PhaseSpectrum phaseSpec, int N) {
		super(N);
		this.amplitudeSpec = amplitudeSpec;
		this.phaseSpec = phaseSpec;
	}

	@Override
	protected ArrayList<Double> calculateData() {
		values = new ArrayList<Double>();

		for (int i = 0; i < getN(); i++) {
			if (amplitudeSpec == null) {
				values.add(amplitude
						* Math.cos(2 * Math.PI * i * frequency / getN()
								- initialPhase));
			} else {
				values.add(calculateSignalCompanent(i));
			}
		}
		return values;
	}

	private Double calculateSignalCompanent(int i) {
		double value = 0;
		for(int j=0; j<getN()/2-1; j++){
			value+=amplitudeSpec.getValues().get(j)*Math.cos(2*Math.PI*j*i/getN() - phaseSpec.getValues().get(j));
		}
		return value;
	}

	@Override
	public ArrayList<Double> getValues() {
		return values;
	}

}
