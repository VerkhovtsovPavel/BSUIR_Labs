package spectrum;

import signals.Drawable;

import java.util.ArrayList;


public class PhaseSpectrum extends Spectrum {
	
	private ArrayList<Double> phaseSpectrum = new ArrayList<>();
	
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

