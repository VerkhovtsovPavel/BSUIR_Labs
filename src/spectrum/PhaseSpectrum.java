package spectrum;

import java.util.ArrayList;

import signals.Drawable;


public class PhaseSpectrum extends Spectrum {
	
	ArrayList<Double> phaseSpectrum = new ArrayList<>();
	
	public PhaseSpectrum(Drawable drawable) {
		super(drawable.getValues());
	}

	@Override
	protected ArrayList<Double> calculateData() {
		for(int i=0; i<Ac.size(); i++){
			phaseSpectrum.add(Math.atan(As.get(i)/Ac.get(i)));
		}
		
		return phaseSpectrum;
	}

	@Override
	public ArrayList<Double> getValues() {
		return this.phaseSpectrum;
	}

}

