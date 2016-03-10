package approximation;

import java.util.ArrayList;

import signals.Drawable;

public class ParaboloidFourthDegree extends Drawable {
	private ArrayList<Double> values;
	private ArrayList<Double> sourceValues;
	
	public ParaboloidFourthDegree(Drawable source) {
		super(source.getN());
		this.sourceValues = source.getValues();
	}

	@Override
	public ArrayList<Double> getValues() {
		return values;
	}

	@Override
	protected ArrayList<Double> calculateData() {
		this.values = new ArrayList<>();
		for(int i=4; i<getN()-4; i++){
			values.add((15*sourceValues.get(i-4)-55*sourceValues.get(i-3)+30*sourceValues.get(i-2)+135*sourceValues.get(i-1)+179*sourceValues.get(i)+135*sourceValues.get(i+1)+30*sourceValues.get(i+2)-55*sourceValues.get(i+3)+15*sourceValues.get(i+4))/429);
		}
		return values;
	}

}
