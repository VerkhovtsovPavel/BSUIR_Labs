package Matrix;

public class Matrix {
	private final double eps = 1e-21;
	private int matrixExtent = 3;
	private double[][] systemCoefficients =  {{2.18, 2.44, 2.49, -4.34},
											  {2.17, 2.31, 2.49, -3.91},
											  {3.15, 3.22, 3.17, -5.27}};; 
	private boolean systemDoesNotHaveSolutions = false;
	private static double freeCoefficients[] = { -4.34, -3.91, -5.27};

//TODO: Check system solvability.

	/**
	 * Empty constructor.
	 */
	public Matrix(){}

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
	 * Search maximum modulus element in column.
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

	/**
	 * Divide all element in row on element on the main diagonal.
	 * @param rowNumber Row number
	 */
	public void divideRow(final int rowNumber){
		double divider = systemCoefficients[rowNumber][rowNumber];
		for(int i = rowNumber; i <= matrixExtent; i++) {
			systemCoefficients[rowNumber][i]/=divider;
		}
	}

	/**
	 * Subtracts the line of all the rows below it.
	 * @param rowNumber Row number
	 */
	public void subtractMatrix(final int rowNumber){
		double subtracted;
		for (int i=rowNumber+1; i<matrixExtent; i++){
			subtracted = systemCoefficients[i][rowNumber];
			for(int j=rowNumber; j<=matrixExtent; j++){
				systemCoefficients[i][j]-=systemCoefficients[rowNumber][j]*subtracted;
			}
		}
	}

	/**
	 * Make answer.
	 * @return answer
	 */
	public double[] makeanswer(){
		double[] answer	= new double[matrixExtent];
		answer[matrixExtent-1]=systemCoefficients[matrixExtent-1][matrixExtent];
		for (int i=matrixExtent-2; i>=0; i--){
			answer[i]=systemCoefficients[i][matrixExtent];
			for(int j=i+1; j<matrixExtent; j++){
				answer[i]-=systemCoefficients[i][j]*answer[j];
			}
		}
		return answer;
	}
}
