package matrix;

/**
 * Class containing base description matrix.
 * @author Pavel_Verkhovtsov
 */
public class Matrix {
	protected boolean systemDoesNotHaveSolutions = false;
	protected int matrixExtent;
	protected double[][] systemCoefficients;
	protected double[] freeCoefficients;

	/**
	 * Constructor.
	 * @param size Matrix size
	 * @param sysCoeff Array of system coefficients
	 */
	public Matrix(final int size, final double[][] sysCoeff){
		this.matrixExtent = size;
		this.systemCoefficients = sysCoeff;
	}
	
	public void checkDontSoluton(int index){
		boolean flag = true;
		for(int j=index; j<systemCoefficients.length; j++){
		for (int i=0; i< systemCoefficients[0].length-1; i++){
			if (systemCoefficients[j][i]!=0){
				flag = false;
			}
		}
		if (flag && (systemCoefficients[j][systemCoefficients.length]!=0)){
			System.out.println("System dont have solution");
			System.exit(1);
		}
	}
	}

	/**
	 * Constructor.
	 * @param size Matrix size
	 * @param sysCoeff Array of system coefficients
	 * @param freeCoeff Array of free coefficients
	 */
	public Matrix(final int size, final double[][] sysCoeff, final double[] freeCoeff){
		this.matrixExtent = size;
		this.systemCoefficients = sysCoeff;
		this.freeCoefficients = freeCoeff;
	}
}
