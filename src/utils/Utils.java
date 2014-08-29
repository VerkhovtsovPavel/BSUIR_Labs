package utils;

/**
 * Class containing helpful methods.
 * @author Pavel_Verkhovtsov
 */
public abstract class Utils {

	/**
	 * Combines matrix and array.
	 * @param firstMatrix Matrix
	 * @param secondMatrix Array
	 * @return Result matrix
	 */
	public static double[][] concatenationMatrix(final double[][] firstMatrix, final double[] secondMatrix){
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

	/**
	 * Verify matrix range.
	 * @param matrix matrix for inspection
	 * @param range Expected matrix range
	 * @return result (true/false)
	 */
	private static boolean verifyMatrixRange(final double[][] matrix, final int range){
		return (matrix.length==range && matrix[0].length==range);
	}

	/**
	 * Verify matrix range.
	 * @param array array for inspection
	 * @param range Expected matrix range
	 * @return result (true/false)
	 */
	private static boolean verifyMatrixRange(final double[] array, final int range){
		return (array.length==range);
	}

	/**
	 * Verify range of matrix and array.
	 * @param matrix matrix for inspection
	 * @param array array for inspection
	 * @param range Expected range
	 */

	public static void verifyMatrixRange(final double[][] matrix, final double[] array, final int range){
		if (!(verifyMatrixRange(matrix, range) && Utils.verifyMatrixRange(array, range))){
			System.out.println("Error in matrix range");
			System.exit(1);
		}
	}

	/**
	 * Transposition matrix
	 * @param matrix original matrix
	 * @return transposed matrix
	 */
	public static double[][] matrixTransposition(final double[][] matrix){
		double[][] tMatrix = new double[matrix.length][matrix[0].length];
		for(int i=0; i<matrix.length; i++){
			for (int j=0; j<matrix[0].length; j++){
				tMatrix[i][j] = matrix[j][i];
			}
		}
		return tMatrix;
	}

	/**
	 * Multiplication matrix
	 * @param firstMatrix First matrix
	 * @param secondMatrix Second matrix
	 * @return result matrix
	 */
	public static double[][] multiplicationMatrix(final double[][] firstMatrix, final double[][] secondMatrix){
		double[][] result = new double[firstMatrix.length][firstMatrix.length];
		for (int i=0; i < result.length; i++){
			for (int j=0; j < result.length; j++){
				result[i][j] = 0;
				for(int k=0; k < result.length; k++){
					result[i][j]+=firstMatrix[i][k]*secondMatrix[k][j];
				}
			}
		}
		return result;
	}

	/**
	 * Multiplication matrix and array
	 * @param array Array
	 * @param matrix Matrix
	 * @return result array
	 */
	public static double[] multiplicationMatrix(final double[] array, final double[][] matrix) {
		double[] result = new double[matrix.length];
		for (int i=0; i < result.length; i++){
			result[i] = 0;
			for(int k=0; k < matrix[i].length; k++){
					result[i]+=matrix[i][k]*array[k];
				}
			}
		return result;
	}

	/**
	 * Make matrix symmetric.
	 * @param matrix original matrix
	 * @return symmetric matrix
	 */
	public static double[][] makeMatrixSymmetric(final double[][] matrix){
		return Utils.multiplicationMatrix(Utils.matrixTransposition(matrix), matrix);
	}

	public static boolean checkSymmetric(final double[][] matrix){
		for (int i=0;i< matrix.length; i++){
			for(int j=0; j<i; j++){
				if (matrix[i][j]!=matrix[j][i]){
					return false;
				}
			}
		}
		return true;
	}
}
