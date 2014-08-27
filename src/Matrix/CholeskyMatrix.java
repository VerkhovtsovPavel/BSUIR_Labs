package matrix;

/**
 * Class containing methods for solving a system of equations by Cholesky.
 * @author Pavel_Verkhovtsov
 */

public class CholeskyMatrix extends Matrix{

	/**
	 * Constructor.
	 * @param size Matrix size
	 * @param sysCoeff Array of system coefficients
	 */
	public CholeskyMatrix(final int size, final double[][] sysCoeff) {
		super(size, sysCoeff);
	}

}
