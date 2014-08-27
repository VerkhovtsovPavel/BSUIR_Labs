package matrix;

/**
 * Class containing base description matrix.
 * @author Pavel_Verkhovtsov
 */
public class Matrix {
	protected final double eps = 1e-21;
	protected int matrixExtent = 3;
	protected double[][] systemCoefficients;
	protected boolean systemDoesNotHaveSolutions = false;
//	private static double freeCoefficients[] = { -4.34, -3.91, -5.27};

	/**
	 * Constructor.
	 * @param size Matrix size
	 * @param sysCoeff Array of system coefficients
	 */
	public Matrix(final int size, final double[][] sysCoeff){
		this.matrixExtent = size;
		this.systemCoefficients = sysCoeff;
	}
}
