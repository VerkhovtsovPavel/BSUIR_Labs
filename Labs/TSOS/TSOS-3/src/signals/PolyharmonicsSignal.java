package signals;

import java.util.ArrayList;

import random.SignalRandomParameters;
import spectrum.AmplitudeSpectrum;
import spectrum.PhaseSpectrum;

public class PolyharmonicsSignal extends Drawable {

	private ArrayList<SimpleSignal> signalHarmonics;
	ArrayList<Double> values;
	private AmplitudeSpectrum amplitudeSpec;
	private PhaseSpectrum phaseSpec;
	private boolean ignoreInitialPhare;

	public PolyharmonicsSignal(int N) {
		super(N);
		this.signalHarmonics = createHarmonics(30);
	}

	public PolyharmonicsSignal(AmplitudeSpectrum amplitudeSpec,
			PhaseSpectrum phaseSpec, boolean isIgnoreInitialPhare, int N) {
		super(N);
		this.amplitudeSpec = amplitudeSpec;
		this.phaseSpec = phaseSpec;
		this.ignoreInitialPhare = isIgnoreInitialPhare;
	}

	private ArrayList<SimpleSignal> createHarmonics(int count) {
		ArrayList<SimpleSignal> harmonics = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			harmonics.add(new SimpleSignal(SignalRandomParameters
					.getRandomAmplitude(), SignalRandomParameters
					.getRandomInitialPhase(), i + 1, getN()));
		}
		return harmonics;
	}

	@Override
	protected ArrayList<Double> calculateData() {
		if (amplitudeSpec == null) {
			values = signalHarmonics.get(0).calculateData();
			for (int i = 1; i < signalHarmonics.size(); i++) {
				companeSignals(values, signalHarmonics.get(i).calculateData());
			}
		} else {
			values = new ArrayList<Double>();
			for (int i = 0; i < getN(); i++) {
				values.add(calculateSignalCompanent(i));
			}
		}
		return values;
	}

	private Double calculateSignalCompanent(int i) {
		double value = 0;
		for (int j = 1; j < getN() / 2 - 1; j++) {
			if (ignoreInitialPhare) {
				value += amplitudeSpec.getValues().get(0) / 2
						+ amplitudeSpec.getValues().get(j)
						* Math.cos(2 * Math.PI * j * i / getN());
			} else {
				value += amplitudeSpec.getValues().get(0)
						/ 2
						+ amplitudeSpec.getValues().get(j)
						* Math.cos(2 * Math.PI * j * i / getN()
								- phaseSpec.getValues().get(j));
			}
		}
		return value;
	}

	private void companeSignals(ArrayList<Double> values,
			ArrayList<Double> calculateData) {
		for (int i = 0; i < calculateData.size(); i++) {
			values.set(i, values.get(i) + calculateData.get(i));
		}
	}

	@Override
	public ArrayList<Double> getValues() {
		return this.values;
	}
}
