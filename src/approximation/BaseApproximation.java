package approximation;

public abstract class BaseApproximation {
	protected double[] arrayX;
	protected double[] arrayY;

	public abstract double approximazeFunction(final double point);

	public BaseApproximation(final double[] xArray, final double[] yArray){
		this.arrayX = xArray.clone();
		this.arrayY = yArray.clone();
	}

	public int getArraySize(){
		return arrayX.length;
	}
}
