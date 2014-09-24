package main;

import graphics.GraphicsClass;
import java.util.Scanner;
import approximation.Lagrange;
import approximation.Newton;

/**
 * Main class
 *
 * @author Pavel_Verkhovtsov
 */
public class Main {

	private static double[] y = { 2.02, 1.98, 1.67, 1.65, 1.57, 1.42, 1.37, 1.07, 0.85, 0.48, 0.35, -0.30, -0.61, -1.2, -1.39, -1.76, -2.28, -2.81,
			-3.57, -4.06 };
	private static double[] x = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };

	private static Scanner in = new Scanner(System.in);

	/**
	 * Main method.
	 *
	 * @param args
	 *            console parameters
	 */
	public static void main(final String[] args) {
		Lagrange lagrange = new Lagrange(x, y);
		Newton newton = new Newton(x, y);

		  GraphicsClass.setScreenSize(700);
		  GraphicsClass.buildGraph(newton, "Newton");
		  GraphicsClass.buildGraph(lagrange, "Lagrange");


		/*System.out.print("Input approximation point: ");
		double point = in.nextDouble();
		System.out.println(newton.approximazeFunction(point));
		System.out.println(lagrange.approximazeFunction(point));*/
	}
}
