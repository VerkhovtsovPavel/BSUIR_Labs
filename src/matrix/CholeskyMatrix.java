package matrix;

import utils.Utils;

/**
 * Class containing methods for solving a system of equations by Cholesky.
 * @author Pavel_Verkhovtsov
 */

public class CholeskyMatrix extends Matrix{
private double[][] lMatrix;
	/**
	 * Constructor.
	 * @param size Matrix size
	 * @param sysCoeff Array of system coefficients
	 * @param freeCoeff Array of free coefficients
	 */
	public CholeskyMatrix(final int size, final double[][] sysCoeff, final double[] freeCoeff) {
		super(size, sysCoeff, freeCoeff);
	}

	/**
	 * Make L-Matrix
	 */
	public void makeLMatrix(){
		for (int i=0; i<matrixExtent; i++){
			for (int j=0; j<=i; j++){
					if(i!=j){
						systemCoefficients[i][j]=subtractRange(i, j)/systemCoefficients[j][j];
					}else{
						systemCoefficients[i][i]=Math.sqrt(subtractRange(i, j));
					}
			}
		}
		lMatrix=Utils.matrixTransposition(systemCoefficients);
	}

	/**
	 * Subtract range of target cell.
	 * @param firstIndex First index
	 * @param secondIndex Second index
	 * @return result value
	 */
	public double subtractRange(final int firstIndex, final int secondIndex){
		double result = systemCoefficients[firstIndex][secondIndex];
			for(int k = 0; k<=secondIndex-1; k++){
				result-=systemCoefficients[firstIndex][k]*systemCoefficients[secondIndex][k];
			}
		return result;
	}

	/**
	 * Finding the solution of the system.
	 * Forward and reverse passes
	 */
	public void solveSystem(){
		for (int i=0; i<matrixExtent; i++){
			divideRow(i);
			for (int j=0; j<i ; j++){
				freeCoefficients[i]-=systemCoefficients[i][j]*freeCoefficients[j];
			}
			verifySolvabilitySystem(i);
		}

		systemCoefficients = lMatrix;

		for(int i=matrixExtent-1; i>=0; i--){
			divideRow(i);
			for(int j=matrixExtent-1; j>i; j--){
				freeCoefficients[i]-=systemCoefficients[i][j]*freeCoefficients[j];
			}
		}
	}
		/**
		 * Check if a solution of the system.
		 * @param index Cell index
		 */
		private void verifySolvabilitySystem(final int index){
		if (systemCoefficients[index][index]==0){
			systemDoesNotHaveSolutions=true;
			System.out.println("Error! Element in main diagonal equal to zero");
			System.exit(1);// TODO Maybe refactor.
		}
	}
	/**
	 * Return array containing system solution
	 * @return system solution
	 */
	public double[] makeAnswer(){
		return freeCoefficients;
	}

	/**
	 * Divide all element in row on element on the main diagonal.
	 * @param rowNumber Row number
	 */
	public void divideRow(final int rowNumber){
		double divider = systemCoefficients[rowNumber][rowNumber];
		for(int i = 0; i < matrixExtent; i++) {
			systemCoefficients[rowNumber][i]/=divider;
		}
		freeCoefficients[rowNumber]/=divider;
	}

}
