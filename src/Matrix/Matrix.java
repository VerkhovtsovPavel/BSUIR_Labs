package Matrix;

public class Matrix {
	private final double eps = 1e-21;
	private int matrixExtent = 3;
	private double[][] systemCoefficients  = {{2.18, 2.44, 2.49},
											  {2.17, 2.31, 2.49},
											  {3.15, 3.22,3.17 },
											  {-4.34, -3.91, -5.27}};

	private boolean systemDoesNotHaveSolutions = false;
	//	private static double freeCoefficients[] = { -4.34, -3.91, -5.27};

//TODO: Check system solvability.

	/**
	 * Constructor.
	 * @param size Matrix size
	 * @param sysCoeff Array of system coefficients
	 * @param freeCoeff Array of free coefficients
	 */
	public Matrix(final int size, final double[][] sysCoeff, final double[] freeCoeff){
		this.matrixExtent = size;
		this.systemCoefficients = sysCoeff;
		this.systemCoefficients[matrixExtent]= freeCoeff;
	}

	/**
	 * Change element in the row with maximum element.
	 * @param rowNumber Number of row
	 */
	public void swapRow(final int rowNumber){
		double buffer;
		int maxIndex = getIndexOfMaxElement(rowNumber);
		for(int j=rowNumber; j<=matrixExtent; j++){
			buffer=systemCoefficients[rowNumber][j];
			systemCoefficients[rowNumber][j] = systemCoefficients[maxIndex][j];
			systemCoefficients[maxIndex][j]=buffer;
		}
	}

	/**
	 * Search maximum modulus element in row.
	 * @param rowNumber Number of row
	 * @return index maximum modulus element
	 */
	private int getIndexOfMaxElement(final int rowNumber){
	    double max = Math.abs(systemCoefficients[rowNumber][rowNumber]);
		int maxIndex = rowNumber;
		for (int i=rowNumber+1; i<matrixExtent; i++){
			if(Math.abs(systemCoefficients[i][rowNumber]) > max){
				max = Math.abs(systemCoefficients[i][rowNumber]);
				maxIndex = i;
			}
		}

		if(max < eps){
			systemDoesNotHaveSolutions = true;
		}
		return maxIndex;
	}
	
	public void divideRow(final int rowNumber){
		
	} 
	
	
}
