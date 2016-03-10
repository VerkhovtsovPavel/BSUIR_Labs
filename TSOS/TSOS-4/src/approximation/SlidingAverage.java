package approximation;

import java.util.ArrayList;

import signals.Drawable;

public class SlidingAverage extends Drawable {

	private ArrayList<Double> values;
	private ArrayList<Double> sourceValues;
	private int k;
	
	public SlidingAverage(Drawable source, int k) {
		super(source.getN());
		this.sourceValues = source.getValues();
		this.k = k;
	}

	@Override
	public ArrayList<Double> getValues() {
		return values;
	}

	@Override
	protected ArrayList<Double> calculateData() {
		values = new ArrayList<>();
		for(int i=k/2; i< getN()-k/2; i++){
			values.add(calculateAverageValue(i));
		}
		
		return values;
	}

	private Double calculateAverageValue(int i) {
		double value =0;
		for(int j=i-k/2; j<i+k/2;j++){
			value+=sourceValues.get(j);
		}
		return value/k;
	}

}
