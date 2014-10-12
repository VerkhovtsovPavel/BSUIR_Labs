package main;

import static main.Main.differentiableFunction;

public class Differentiator {

	private int highInterval;
	private int lowInterval;
	private final int numberOfIntervals = 10000;

	public Differentiator(int hInterval, int lInterval) {
		this.highInterval = hInterval;
		this.lowInterval = lInterval;
	}

	public double getFirstDerivative(double x) {
		double firstDiff = 0;
		double step = (double) (highInterval - lowInterval) / numberOfIntervals;

		int i = (int) ((x - lowInterval) / step + step / 2);

		if (i == 0) {
			firstDiff = (-3 * differentiableFunction(lowInterval) + 4
					* differentiableFunction(lowInterval + step) - differentiableFunction(lowInterval
					+ 2 * step))
					/ (2 * step);
		} else if (i > 0 && i < numberOfIntervals) {
			firstDiff = (-differentiableFunction(lowInterval + (step * (i - 1))) + differentiableFunction(lowInterval
					+ (step * (i + 1))))
					/ (2 * step);
		} else if (i == numberOfIntervals) {
			firstDiff = (differentiableFunction(lowInterval
					+ (step * (numberOfIntervals - 3)))
					- 4
					* differentiableFunction(lowInterval
							+ (step * (numberOfIntervals - 2))) + 3 * differentiableFunction(highInterval))
					/ (2 * step);
		}

		else {
			System.out.println("Point out of range");
			System.exit(1);
		}
		return firstDiff;
	}

	public double getSecondDerivative(double x) {
		double secondDiff = 0;
		double step = (double) (highInterval - lowInterval) / numberOfIntervals;

		int i = (int) ((x - lowInterval) / step + step / 2);

		if (i == 0) {
			secondDiff = (2 * differentiableFunction(lowInterval) - 5
					* differentiableFunction(lowInterval + step) + 4
					* differentiableFunction(lowInterval + 2 * step) - differentiableFunction(lowInterval
					+ 3 * step))
					/ (step * step);
		} else if (i > 0 && i < numberOfIntervals) {
			secondDiff = (differentiableFunction(lowInterval + (i - 1) * step)
					- 2 * differentiableFunction(lowInterval + i * step) + differentiableFunction(lowInterval
					+ (i + 1) * step))
					/ (step * step);
		} else if (i == numberOfIntervals) {
			secondDiff = (-differentiableFunction(lowInterval
					+ (numberOfIntervals - 4) * step)
					+ 4
					* differentiableFunction(lowInterval
							+ (numberOfIntervals - 3) * step)
					- 5
					* differentiableFunction(lowInterval
							+ (numberOfIntervals - 2) * step) + 2 * differentiableFunction(lowInterval
					+ (numberOfIntervals - 1) * step))
					/ (step * step);
		} else {
			System.out.println("Point out of range");
			System.exit(1);
		}
		return secondDiff;
	}
}
