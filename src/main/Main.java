package main;

import java.util.Scanner;

public class Main {

	private static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.print("Input low interval: ");
		int lowInterval = in.nextInt();
	
		System.out.print("Input high interval: ");
		int highInterval = in.nextInt();
		
		System.out.print("Input point: ");
		double point = in.nextDouble();
		

		Integrator integrator = new Integrator(highInterval, lowInterval);
		System.out.println(integrator.integrateBySimpson());
		System.out.println(integrator.integrateByGauss());
		
		IntegratorVersion2 integratorVersion2 = new IntegratorVersion2(lowInterval, highInterval, 7);
		System.out.println(integratorVersion2.computeIntegralSimpson());
		System.out.println(integratorVersion2.computeIntegralGauss());
		
	/*	Differentiator differentiator = new Differentiator(highInterval, lowInterval);
		System.out.println(differentiator.getFirstDerivative(point));
		System.out.println(differentiator.getSecondDerivative(point));*/
	}

	public static double integrableFunction(double point) {
		return Math.cos(2 * point) / (2 * Math.sin(point));
	}
	
	public static double differentiableFunction(double point) {
		return Math.log(point)+Math.cos(point)/point;
	}
}
