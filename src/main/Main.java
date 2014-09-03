package main;

import java.util.Scanner;
import utils.Utils;
import matrix.CholeskyMatrix;
import matrix.GaussMatrix;

/**
 * Main class.
 * @author Pavel_Verkhovtsov
 */
public class Main{
	private static int range;
	private static double[][] inputMatrix;/*  = {{2.18, 2.44, 2.49},
											 {2.17, 2.31, 2.49},
											 {3.15, 3.22, 3.17}};*/

	private static double[] freeCoefficients;/* = {-4.34, -3.91, -5.27};*/

	/**
	 * Main method.
	 * @param args console arguments
	 */
	@SuppressWarnings("resource")
	public static void main(final String[] args){
		System.out.print("Input matrix range: ");
		range = new Scanner(System.in).nextInt();

		inputMatrix = inputSystemCoefficients(range);
		freeCoefficients = inputFreeCoefficients(range);

		Utils.verifyMatrixRange(inputMatrix, freeCoefficients, range);
		GaussMatrix gaussMatrix = new GaussMatrix(range, Utils.concatenationMatrix(inputMatrix, freeCoefficients));
		for (int i=0; i<range; i++){
			gaussMatrix.swapRow(i);
			gaussMatrix.divideRow(i);
			gaussMatrix.subtractMatrix(i);
			gaussMatrix.printStep(i); //TODO Bad output when long numbers.
		}

		double[] answer = gaussMatrix.makeAnswer();
		System.out.println("Gauss answer:");
		for(int i=0; i<answer.length; i++){
			System.out.println(answer[i]);
		}

		if (!Utils.checkSymmetric(inputMatrix)){
			freeCoefficients = Utils.multiplicationMatrix(freeCoefficients, Utils.matrixTransposition(inputMatrix));
			inputMatrix = Utils.makeMatrixSymmetric(inputMatrix);
		}

		// TODO Add complex value.
		CholeskyMatrix choleskyMatrix = new CholeskyMatrix(range, inputMatrix, freeCoefficients);
		choleskyMatrix.makeLMatrix();
		choleskyMatrix.solveSystem();

		System.out.println("\nCholesky answer:");
		answer = choleskyMatrix.makeAnswer();
		for(int i=0; i<answer.length; i++){
			System.out.println(answer[i]);
		}
	}

	/**
	 * Input free coefficients.
	 * @param size array range
	 * @return filling array
	 */
	private static double[] inputFreeCoefficients(final int size) {
		System.out.println("Input free coefficients:");
		Scanner in = new Scanner(System.in);
		double[] result = new double[size];
		for (int i=0; i<size; i++){
			result[i]=in.nextDouble();
			}
		return result;
	}

	/**
	 * Input systems coefficients.
	 * @param size matrix range
	 * @return filling matrix
	 */
	private static double[][] inputSystemCoefficients(final int size) {
		System.out.println("Input system coefficients:");
		Scanner in = new Scanner(System.in);
		double[][] result = new double[size][size];
		for (int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				result[i][j]=in.nextDouble();
			}
		}
		return result;
	}
}
