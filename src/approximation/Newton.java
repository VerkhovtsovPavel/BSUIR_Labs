package approximation;

public class Newton extends BaseApproximation{

	/**
	 * Constructor
	 * @param xArray Array of x coordinate
	 * @param yArray Array of y coordinate
	 */
	public Newton(final double[] xArray, final double[] yArray){
		//TODO Add check equal intervals
		super(xArray, yArray);
	}

	@Override
	public double approximazeFunction(final double x){
		double[] xArray = this.arrayX.clone();
		double[] yArray = this.arrayY.clone();
		double valueInPoint=yArray[0];
		double polinom=1;

		for (int k=1; k<xArray.length; k++){
			polinom=polinom*(x-xArray[k-1]);
			for (int i=0; i<(xArray.length-k); i++){
				yArray[i]=(yArray[i+1]-yArray[i])/(xArray[i+k]-xArray[i]);
			}
			valueInPoint=valueInPoint+polinom*yArray[0];
		}
		return valueInPoint;
    }
}
