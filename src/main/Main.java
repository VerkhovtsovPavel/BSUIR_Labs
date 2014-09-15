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
	private static boolean swapFlag = false;

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
		//solveByGauss();
		solveByCholesky();
		}

		private static void solveByCholesky() {
			if (Utils.checkMainDiagonal(inputMatrix)){
				System.out.println("Matrix containts zero in the main diagonal. System can not be solved by Cholesky");
			}
			else{
				if (!Utils.checkSymmetric(inputMatrix)){
					System.out.println("Matrix not diagonal.\nMatrix will be presented in the dialonal form.");
					freeCoefficients = Utils.multiplicationMatrix(freeCoefficients, Utils.matrixTransposition(inputMatrix));
					inputMatrix = Utils.makeMatrixSymmetric(inputMatrix);
				}

				CholeskyMatrix choleskyMatrix = new CholeskyMatrix(range, inputMatrix, freeCoefficients);
				choleskyMatrix.makeLMatrix();
				choleskyMatrix.solveSystem();
				// TODO Add complex value.

				System.out.println("\nCholesky answer:");
				double[] answer = choleskyMatrix.makeAnswer();
				for(int i=0; i<answer.length; i++){
					System.out.println(answer[i]);
				}
			}
	}

		private static void solveByGauss(){
			if (Utils.checkMainDiagonal(inputMatrix)){
				System.out.println("Zero on the main diagonal. Gayss method can find system solution. Use methos max element in row.");
				swapFlag=true;
			}

			GaussMatrix gaussMatrix = new GaussMatrix(range, Utils.concatenationMatrix(inputMatrix, freeCoefficients));

			for (int i=0; i<range; i++){
				if (swapFlag){
					gaussMatrix.swapRow(i);
				}
				gaussMatrix.checkZeroRow(i);
				gaussMatrix.divideRow(i);
				gaussMatrix.checkZeroRow(i);
				gaussMatrix.subtractMatrix(i);
				gaussMatrix.printStep(i); //TODO Bad output when long numbers.
			}

			double[] answer = gaussMatrix.makeAnswer();
			System.out.println("Gauss answer:");
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
