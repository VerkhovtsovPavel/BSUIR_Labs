package utils;

/**
 * Class containing helpful methods.
 * @author Pavel_Verkhovtsov
 */
public class Utils {

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
	public static boolean verifyMatrixRange(final double[][] matrix, final int range){
		return (matrix.length==range && matrix[0].length==range);
	}

	/**
	 * Verify matrix range.
	 * @param matrix matrix for inspection
	 * @param range Expected matrix range
	 * @return result (true/false)
	 */
	public static boolean verifyMatrixRange(final double[] matrix, final int range){
		return (matrix.length==range);
	}
}
