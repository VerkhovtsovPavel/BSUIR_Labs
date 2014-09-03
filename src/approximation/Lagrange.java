package approximation;

/**
 * Class contained methods to approximate function by Lagrange.
 * @author Pavel_Verkhovtsov
 */
public class Lagrange {
	private double[] arrayX;
	private double[] arrayY;



	/**
	 * Constructor
	 * @param xArray Array of x coordinate
	 * @param yArray Array of y coordinate
	 */
	public Lagrange(final double[] xArray, final double[] yArray){
		// TODO Normal names of variable (p,point,s)
		arrayX=xArray;
		arrayY=yArray;
	}

	/**
	 * Approximate function by Lagrange.
	 * @param point point of approximated
	 * @return value approximated function in point
	 */
	public double approximazeFunction(final double point){
		double valueInPoint = 0;
		for(int i=0; i<arrayX.length; i++){
			double s=1;
			for(int j=0; j<arrayY.length; j++){
				if (i!=j){
					s*=(point-arrayX[j])/(arrayX[i]-arrayX[j]);
				}
			}
			valueInPoint+=arrayY[i]*s;
		}
		return valueInPoint;
	}
}