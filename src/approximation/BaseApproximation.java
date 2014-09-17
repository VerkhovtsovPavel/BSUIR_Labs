package approximation;

import java.lang.reflect.Array;

public abstract class BaseApproximation {
	protected double[] arrayX;
	protected double[] arrayY;
	
	public abstract double approximazeFunction(final double point);



	public BaseApproximation(final double[] xArray, final double[] yArray){
		this.arrayX = xArray.clone();
		this.arrayY = yArray.clone();
	}
}
