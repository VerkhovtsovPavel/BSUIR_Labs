package approximation;

import java.util.ArrayList;
import java.util.Collections;

import signals.Drawable;

public class MedianFiltering extends Drawable {
	private ArrayList<Double> values;
	private ArrayList<Double> sourceValues;
	private int k;

	public MedianFiltering( Drawable source, int k) {
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
		for(int i=k/2; i<getN()-k/2; i++){
			values.add(getMedianValue(i));
		}
		return values;
	}

	private Double getMedianValue(int i) {
		ArrayList<Double> tempList = new ArrayList<>();
		for(int j=i-k/2; j<i+k/2; j++){
			tempList.add(sourceValues.get(j));
		}
		Collections.sort(tempList);
		return tempList.get(k/2+1);
	}

}
