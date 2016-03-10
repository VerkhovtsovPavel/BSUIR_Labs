package approximation;

/**
 * Class contained methods to approximate function by Lagrange.
 *
 * @author Pavel_Verkhovtsov
 */
public class Lagrange extends BaseApproximation {

	/**
	 * Constructor
	 *
	 * @param xArray
	 *            Array of x coordinate
	 * @param yArray
	 *            Array of y coordinate
	 */
	public Lagrange(final double[] xArray, final double[] yArray) {
		super(xArray, yArray);
	}

	/**
	 * Approximate function by Lagrange.
	 *
	 * @param point
	 *            point of approximated
	 * @return value approximated function in point
	 */
	@Override
	public double approximazeFunction(final double point) {
		checkInterval(point);

		double valueInPoint = 0;
		for (int i = 0; i < arrayX.length; i++) {
			double polinom = 1;
			for (int j = 0; j < arrayY.length; j++) {
				if (i != j) {
					polinom *= (point - arrayX[j]) / (arrayX[i] - arrayX[j]);
				}
			}
			valueInPoint += arrayY[i] * polinom;
		}
		return valueInPoint;
	}
}