package approximation;

public class Newton extends BaseApproximation {

	/**
	 * Constructor
	 * 
	 * @param xArray
	 *            Array of x coordinate
	 * @param yArray
	 *            Array of y coordinate
	 */
	public Newton(final double[] xArray, final double[] yArray) {
		super(xArray, yArray);
		checkeEqualSteps();
	}

	@Override
	public double approximazeFunction(final double point) {
		checkInterval(point);
		double[] xArray = this.arrayX.clone();
		double[] yArray = this.arrayY.clone();
		double valueInPoint = yArray[0];
		double polinom = 1;

		for (int i = 1; i < xArray.length; i++) {
			polinom = polinom * (point - xArray[i - 1]);
			for (int j = 0; j < (xArray.length - i); j++) {
				yArray[j] = (yArray[j + 1] - yArray[j]) / (xArray[j + i] - xArray[j]);
			}
			valueInPoint = valueInPoint + polinom * yArray[0];
		}
		return valueInPoint;
	}

	private void checkeEqualSteps() {
		double step = arrayX[1] - arrayX[0];
		for (int i = 1; i < arrayX.length; i++) {
			if (arrayX[i] - arrayX[i - 1] != step) {
				System.out.println("Newton's interpolation polynomial can only work an equal step of arguments");
				System.exit(1);
			}
		}
	}
}
