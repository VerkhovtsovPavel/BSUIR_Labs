package main;

import graphics.GraphicsClass;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

	//TODO Check medain working
	//TODO Add check sum probability = 1
	private static Scanner in = new Scanner(System.in);
	private final static int SELECTION_SIZE = 1000;
	private static int median; 

	public static void main(String args[]) {
		System.out.print("Enter probability of the first class:\t");
		double probalityOfFirstClass = in.nextDouble();
		System.out.print("Enter probability of the second class:\t");
		double probalityOfSecondClass = in.nextDouble();

		Probability firstProbability = new Probability(generateRandomVariable(0, 1000), probalityOfFirstClass);
		Probability secondProbability = new Probability(generateRandomVariable(100, 1000), probalityOfSecondClass);

		System.out.println("False alarm error:\t"+falseAlarmError(firstProbability, probalityOfFirstClass, secondProbability, probalityOfSecondClass));
		System.out.println("Missing detecting error:\t"+missingDetectingError(firstProbability, probalityOfFirstClass));
		System.out.println("Total error:\t"+(missingDetectingError(firstProbability, probalityOfFirstClass)
				+ falseAlarmError(firstProbability, probalityOfFirstClass, secondProbability, probalityOfSecondClass)));

		GraphicsClass.buildGraph(firstProbability, secondProbability, median);

	}

	private static ArrayList<Integer> generateRandomVariable(int startValue, int dispersion) {
		ArrayList<Integer> randomVariable = new ArrayList<Integer>();
		Random random = new Random();
		for (int i = 0; i < SELECTION_SIZE; i++) {
			randomVariable.add(random.nextInt(dispersion) + startValue);
		}
		return randomVariable;
	}

	private static double missingDetectingError(Probability firstProbability, double probalityOfFirstClass) {
		final double  step = 0.001;
		double x = 0;
		double missingDetecting = 0;
		while (x < 1000) {
			double probability = firstProbability.probabilityDensity(x);
			missingDetecting += probability * step * probalityOfFirstClass;
			x += step;
		}
		return missingDetecting;
	}

	private static double falseAlarmError(Probability firstProbability, double probalityOfFirstClass, Probability secondProbability,
			double probalityOfSecondClass) {
		final double  step = 0.001;
		double x = 0;
		double relativeProbabilityOfFirstClass = 1;
		double relativeProbabilityOfSecondClass = 0;
		double falseAlarm = 0;

		while (relativeProbabilityOfSecondClass < relativeProbabilityOfFirstClass) {
			relativeProbabilityOfFirstClass = probalityOfFirstClass * firstProbability.probabilityDensity(x);
			relativeProbabilityOfSecondClass = probalityOfSecondClass * secondProbability.probabilityDensity(x);
			falseAlarm += relativeProbabilityOfSecondClass * step;
			x += step;
		}
		
		median = (int)x;

		return falseAlarm;
	}
}
