package spectrum;

import java.util.ArrayList;
import java.util.List;

public class PhaseSpectrum extends Spectrum {
	
	private List<Double> phaseSpectrum = new ArrayList<>();
	private List<Double> as;
	private List<Double> ac;

	public PhaseSpectrum(List<Double> as, List<Double> ac, int n) {
		super(n);
		this.as = as;
		this.ac = ac;
	}

	public PhaseSpectrum(List<Double>value, int n) {
		super(n);
		this.phaseSpectrum = value;
	}

	private void calculateData() {
		for(int i = 0; i< ac.size(); i++){
			phaseSpectrum.add(Math.atan(as.get(i)/ ac.get(i)));
		}
	}

	@Override
	public List<Double> getValues() {
		if(phaseSpectrum.isEmpty()){
			calculateData();
		}
		return this.phaseSpectrum;
	}
}

