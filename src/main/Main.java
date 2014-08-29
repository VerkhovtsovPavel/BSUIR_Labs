package main;

import utils.Utils;
import matrix.CholeskyMatrix;
import matrix.GaussMatrix;

/**
 * Main class.
 * @author Pavel_Verkhovtsov
 */
public class Main{
	private static int range = 3;
	private static double[][] inputMatrix  = {{2.18, 2.44, 2.49},
											 {2.17, 2.31, 2.49},
											 {3.15, 3.22, 3.17}};

	private static double[] freeCoefficients = {-4.34, -3.91, -5.27};

	/**
	 * Main method.
	 * @param args console arguments
	 */
	public static void main(final String[] args){
		Utils.verifyMatrixRange(inputMatrix, freeCoefficients, range);

	/*	freeCoefficients = Utils.multiplicationMatrix(freeCoefficients, Utils.matrixTransposition(inputMatrix));
		inputMatrix = Utils.makeMatrixSymmetric(inputMatrix);*/

		GaussMatrix gaussMatrix = new GaussMatrix(range, Utils.concatenationMatrix(inputMatrix, freeCoefficients));
		for (int i=0; i<range; i++){
			gaussMatrix.swapRow(i);
			gaussMatrix.divideRow(i);
			gaussMatrix.subtractMatrix(i);
		}

		double[] answer = gaussMatrix.makeAnswer();
		System.out.println("Gauss answer:");
		for(int i=0; i<answer.length; i++){
			System.out.println(answer[i]);
		}

	    freeCoefficients = Utils.multiplicationMatrix(freeCoefficients, Utils.matrixTransposition(inputMatrix));
		inputMatrix = Utils.makeMatrixSymmetric(inputMatrix);

		double [][] q = inputMatrix; /*temp*/
		// TODO Manual check system solution.
		CholeskyMatrix choleskyMatrix = new CholeskyMatrix(range, inputMatrix, freeCoefficients);
		choleskyMatrix.makeLMatrix();
		choleskyMatrix.solveSystem();

		System.out.println("\nChelosky answer:");
		answer = choleskyMatrix.makeAnswer();
		for(int i=0; i<answer.length; i++){
			System.out.println(answer[i]);
		}
	}
}
