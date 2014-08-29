package matrix;

/**
 * Class containing base description matrix.
 * @author Pavel_Verkhovtsov
 */
public class Matrix {
	protected final double eps = 1e-21;
	protected boolean systemDoesNotHaveSolutions = false;

	protected int matrixExtent = 3;
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
