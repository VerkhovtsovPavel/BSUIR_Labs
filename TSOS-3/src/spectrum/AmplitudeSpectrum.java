package spectrum;

import java.util.ArrayList;

import signals.Drawable;

public class AmplitudeSpectrum extends Spectrum {
	
	ArrayList<Double> amplitudeSpectrum = new ArrayList<>();
	
	public AmplitudeSpectrum(Drawable drawable) {
		super(drawable.getValues());
	}

	@Override
	protected ArrayList<Double> calculateData() {
		for(int i=0; i<Ac.size(); i++){
			amplitudeSpectrum.add(Math.sqrt(Math.pow(Ac.get(i), 2)+Math.pow(As.get(i), 2)));
		}
		
		return amplitudeSpectrum;
	}

	@Override
	public ArrayList<Double> getValues() {
		return this.amplitudeSpectrum;
	}

}
