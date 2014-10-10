package main;

public class Differentiator {

	private int highInterval;
	private int lowInterval;
	private final int numberOfIntervals = 6;

	public Differentiator(int hInterval, int lInterval) {
		this.highInterval = hInterval;
		this.lowInterval = lInterval;
	}

	public double getFirstDerivative(double x) {
		return 1;
	}	
	
	public double getSecondDerivative(double x) {
		return 2;
	}
}
