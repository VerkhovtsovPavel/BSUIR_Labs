package main;

import approximation.Lagrange;

public class Main {

	private static double[] y = {2.02, 1.98, 1.67, 1.65, 1.57, 1.42, 1.37, 1.07, 0.85, 0.48, 0.35, -0.30, -0.61, -1.2, -1.39, -1.76, -2.28, -2.81, -3.57, -4.06};
	private static double[] x = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

	public static void main(final String[] args){
		Lagrange lagrange = new Lagrange(x, y, 2);
		System.out.println(lagrange.approximazeFunction());
	}
}
