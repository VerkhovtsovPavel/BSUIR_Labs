package main;

import matrix.GaussMatrix;

/**
 * Main class.
 * @author Pavel_Verkhovtsov
 */
public class Main{
	private static int power = 3;
	private static double[][] inputMatrix  = {{2.18, 2.44, 2.49},
											  {2.17, 2.31, 2.49},
											  {3.15, 3.22, 3.17}};

	private static double freeCoefficients[] = { -4.34, -3.91, -5.27};		

	/**
	 * Main method.
	 * @param args console arguments
	 */
	public static void main(final String[] args){
		GaussMatrix matrix = new GaussMatrix(power, concatenationMatrix(inputMatrix, freeCoefficients));
		for (int i=0; i<power/*not good*/; i++){
			matrix.swapRow(i);
			matrix.divideRow(i);
			matrix.subtractMatrix(i);
		}

		double[] answer = matrix.makeanswer();
		for(int i=0; i<answer.length; i++){
			System.out.println(answer[i]);
		}
	}

	/**
	 * Combines matrix and array.
	 * @param firstMatrix Matrix
	 * @param secondMatrix Array
	 * @return Result matrix
	 */
	private static double[][] concatenationMatrix(final double[][] firstMatrix, final double[] secondMatrix){
		double [][] resultMatrix = new double[firstMatrix.length][firstMatrix[0].length+1];
		for (int i = 0; i<firstMatrix.length; i++){
			for (int j = 0; j<firstMatrix[0].length; j++){
				resultMatrix[i][j] = firstMatrix[i][j];
			}
		}

		for (int i = 0; i<firstMatrix.length; i++){
			resultMatrix[i][firstMatrix[0].length] = secondMatrix[i];
		}
		return resultMatrix;
	}
}
