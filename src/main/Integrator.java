package main;

public class Integrator {

	private static double[] coefficientsX = new double[] { -0.960290,
			-0.796666, -0.525532, -0.183434, 0.183434, 0.525532, 0.796666,
			0.960290 };
	private static double[] coefficientsA = new double[] { 0.101228, 0.222381,
			0.313707, 0.362684, 0.362684, 0.313707, 0.222381, 0.101228 };

	private double lowInterval;
	private double highInterval;
	private double eps;

	public Integrator(double a, double b, double eps) {
		this.lowInterval = a;
		this.highInterval = b;
		this.eps = eps;
	}

	public double simpson() {
		double result = 0;
		result = ((highInterval - lowInterval) / 6)
				* (Main.integrableFunction(lowInterval) + 4 * Main
						.integrableFunction((lowInterval + highInterval) / 2))
				+ Main.integrableFunction(highInterval);
		double temp;
		int n = 2;

		while (true) {
			temp = 0;
			double step = Math.abs((lowInterval - highInterval) / n);
			for (double i = lowInterval; Math.abs(i - highInterval) > 0.001; i += step) {
				temp += simpsonMethod(i, i + step);
			}
			if ((Math.abs(result - temp) < eps)) {
				break;
			} else {
				result = temp;
				n++;
			}
		}
		return temp;
	}

	private double simpsonMethod(double a, double b) {
		return ((b - a) / 6)
				* (Main.integrableFunction(a) + 4
						* Main.integrableFunction((a + b) / 2) + Main
							.integrableFunction(b));
	}

	public double gauss() {
		double result = 0;
		result = gaussMethod(lowInterval, highInterval);
		double temp;
		int n = 2;

		while (true) {
			temp = 0;
			double step = Math.abs((lowInterval - highInterval) / n);
			for (double i = lowInterval; Math.abs(i - highInterval) > 0.001; i += step) {
				temp += gaussMethod(i, i + step);
			}
			if ((Math.abs(result - temp) < eps)) {
				break;
			} else {
				result = temp;
				n++;
			}
		}
		return temp;
	}

	private double gaussMethod(double a, double b) {
		double result = ((b - a) / 2);
		double sum = 0;
		for (int i = 0; i < coefficientsX.length; i++) {
			sum += coefficientsA[i]
					* Main.integrableFunction(((b - a) / 2) * coefficientsX[i]
							+ ((a + b) / 2));
		}
		result *= sum;

		return result;
	}
}
