package matrix;

/**
 * Class containing methods for solving a system of equations by Gauss.
 * @author Pavel_Verkhovtsov
 */
public class GaussMatrix extends Matrix{
	private final double eps = 1e-21;
	/**
	 * Constructor.
	 * @param size Matrix size
	 * @param sysCoeff Array of system coefficients
	 */
	public GaussMatrix(final int size, final double[][] sysCoeff) {
		super(size, sysCoeff);
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
			System.out.println("Система не имеет решения");
			systemDoesNotHaveSolutions = true;
			System.exit(1);
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
	public double[] makeAnswer(){
		double[] answer	= new double[matrixExtent];
		answer[matrixExtent-1]=systemCoefficients[matrixExtent-1][matrixExtent];
		for (int i=matrixExtent-2; i>=0; i--){
			answer[i]=systemCoefficients[i][matrixExtent];
			for(int j=matrixExtent-1; j>i; j--){
				answer[i]-=systemCoefficients[i][j]*answer[j];
			}
		}
		return answer;
	}

	/**
	 *  Print matrix on intermediate.
	 * @param stepNumber Number of step
	 */
	public void printStep(int stepNumber){
		System.out.println("### Step #"+(++stepNumber)+" ###");
		for (int i=0; i<matrixExtent; i++){
			for(int j=0; j<=matrixExtent; j++){
				System.out.print(systemCoefficients[i][j]+"\t");
			}
			System.out.println();
		}
	}
}
