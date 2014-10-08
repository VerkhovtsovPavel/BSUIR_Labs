package main;

public class Differentiator {

	private int highInterval;
	private int lowInterval;
	private final int numberOfIntervals = 5;

	public Differentiator(int hInterval, int lInterval) {
		this.highInterval = hInterval;
		this.lowInterval = lInterval;
	}

	public double getFirstDerivative(double x) {
		double step, currentValue, valueInPoint = 0;
		double functionValue[] = new double[numberOfIntervals];
		step = (highInterval - lowInterval) / numberOfIntervals;

		currentValue = lowInterval;
		for (int i = 0; i < functionValue.length - 1; i++) {
			functionValue[i] = Main.integrableFunction(currentValue);
			currentValue = currentValue + step;
		}
		
		int i = (int) ((x - lowInterval) / step + step / 2);
		
		if (i == 0) {
			valueInPoint = (-3 * functionValue[0] + 4 * functionValue[1] - functionValue[2]) / (2 * step);
		}
		if ((i > 0) && (i < numberOfIntervals)) {
			valueInPoint = (-functionValue[i - 1] + functionValue[i + 1]) / (2 * step);
		}
		if (i == numberOfIntervals) {
			valueInPoint = (functionValue[numberOfIntervals - 2] - 4 * functionValue[numberOfIntervals - 1] + 3 * functionValue[numberOfIntervals])
					/ (2 * step);
		}
		return valueInPoint;
	}

	public double getSecondDerivative(double a, double b, double x, int n) {
		double h, h2, aa = 0, y_2 = 0;
		double y[] = new double[n];
		h = (b - a) / n;

		aa = a;
		for (int i = 0; i < y.length - 1; i++) {
			y[i] = Main.integrableFunction(aa);
			aa = aa + h;
		}
		int i = (int) ((x - a) / h + h / 2);
		h2 = h * h;
		if (i == 0) {
			y_2 = (2 * y[0] - 5 * y[1] + 4 * y[2] - y[3]) / h2;
		}
		if ((i > 0) && (i < n)) {
			y_2 = (y[i - 1] - 2 * y[i] + y[i + 1]) / h2;
		}
		if (i == n) {
			y_2 = (-y[n - 3] + 4 * y[n - 2] - 5 * y[n - 1] + 2 * y[n]) / h2;
		}
		return y_2;
	}

}
