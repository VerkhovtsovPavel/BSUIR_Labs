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
		double step = (double) (highInterval - lowInterval) / numberOfIntervals;
		double functionValue[] = new double[numberOfIntervals];
		double currentValue = lowInterval;
		for (int i = 0; i < functionValue.length; i++) {
			functionValue[i] = Main.differentiableFunction(currentValue);
			currentValue = currentValue + step;
		}

		int i = (int) ((x - lowInterval) / step + step / 2);
		// TODO Rename i

		double valueInPoint;
		if (i == 0) {
			valueInPoint = (-3 * functionValue[0] + 4 * functionValue[1] - functionValue[2]) / (2 * step);
		} else if (i == numberOfIntervals) {
			valueInPoint = (functionValue[numberOfIntervals - 2] - 4 * functionValue[numberOfIntervals - 1] + 3 * functionValue[numberOfIntervals])
					/ (2 * step);
		} else {
			valueInPoint = (-functionValue[i - 1] + functionValue[i + 1]) / (2 * step);
		}

		return valueInPoint;
	}

	public double getSecondDerivative(double x) {

		double functionValue[] = new double[numberOfIntervals];
		double step = (double) (highInterval - lowInterval) / numberOfIntervals;

		double currentValue = lowInterval;
		for (int i = 0; i < functionValue.length; i++) {
			functionValue[i] = Main.differentiableFunction(currentValue);
			currentValue = currentValue + step;
		}
		int i = (int) ((x - lowInterval) / step + step / 2);
		// TODO Rename i
		double stepSqr = step * step;
		double valueInPoint;
		if (i == 0) {
			valueInPoint = (2 * functionValue[0] - 5 * functionValue[1] + 4 * functionValue[2] - functionValue[3]) / stepSqr;
		} else if (i == numberOfIntervals) {
			valueInPoint = (-functionValue[numberOfIntervals - 3] + 4 * functionValue[numberOfIntervals - 2] - 5
					* functionValue[numberOfIntervals - 1] + 2 * functionValue[numberOfIntervals])
					/ stepSqr;
		} else {
			valueInPoint = (functionValue[i - 1] - 2 * functionValue[i] + functionValue[i + 1]) / stepSqr;
		}
		return valueInPoint;
	}

}
