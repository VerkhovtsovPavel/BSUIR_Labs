package approximation;

public abstract class BaseApproximation {
	protected double[] arrayX;
	protected double[] arrayY;
	protected double minArgument;
	protected double maxArgument;
	private double argumentsRange;

	public abstract double approximazeFunction(final double point);

	public BaseApproximation(final double[] xArray, final double[] yArray) {
		this.arrayX = xArray.clone();
		this.arrayY = yArray.clone();
		
		checkDublicateArguments();

		double max = Double.MIN_VALUE;
		double min = Double.MAX_VALUE;

		for (int i=0; i<arrayX.length; i++){
			if(arrayX[i]>max){
				max=arrayX[i];
			}
			if(arrayX[i]<min){
				min = arrayX[i];
			}
		}
		this.minArgument = min;
		this.maxArgument = max;
		this.argumentsRange = max -min;
	}

	public int getArraySize() {
		return arrayX.length;
	}

	protected void checkInterval(double point){
		if (point < minArgument || point > maxArgument) {
			System.out.println(String.format("Point %s outside the predetermined interval. Extrapolation is not possible", point));
			System.exit(1);
		}
	}
	
	private void checkDublicateArguments() {
		for (int i=0; i<arrayX.length; i++){
			for (int j=i+1; j<arrayX.length; j++){
				if(arrayX[i]==arrayX[j]){
					System.out.println("Array of arguments containts dublicate values. Interpolation is not possible");
					System.exit(1);
				}
			}
		}
	}

	public double getArgumentsRange(){
		return argumentsRange;
	}

	public double getMin(){
		return minArgument;
	}
	
	public double getMax(){
		return maxArgument;
	}
}
