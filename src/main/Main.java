package main;

import utils.Utils;
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

	private static double[] freeCoefficients = { -4.34, -3.91, -5.27};

	/**
	 * Main method.
	 * @param args console arguments
	 */
	public static void main(final String[] args){
		if (!(Utils.verifyMatrixRange(inputMatrix, range) && Utils.verifyMatrixRange(freeCoefficients, range))){
			System.out.println("Error in matrix range");
			System.exit(1);
		}
		GaussMatrix matrix = new GaussMatrix(range, Utils.concatenationMatrix(inputMatrix, freeCoefficients));
		for (int i=0; i<range; i++){
			matrix.swapRow(i);
			matrix.divideRow(i);
			matrix.subtractMatrix(i);
		}

		double[] answer = matrix.makeanswer();
		for(int i=0; i<answer.length; i++){
			System.out.println(answer[i]);
		}
	}
}
