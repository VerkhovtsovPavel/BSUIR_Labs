package matrix;

import org.omg.CORBA.FREE_MEM;


/**
 * Class containing methods for solving a system of equations by Cholesky.
 * @author Pavel_Verkhovtsov
 */

public class CholeskyMatrix extends Matrix{

	/**
	 * Constructor.
	 * @param size Matrix size
	 * @param sysCoeff Array of system coefficients
	 * @param freeCoeff Array of free coefficients
	 */
	public CholeskyMatrix(final int size, final double[][] sysCoeff, final double[] freeCoeff) {
		super(size, sysCoeff, freeCoeff);
	}
	
	public void a(){
		for (int i=0; i<matrixExtent; i++){
			if (i!=0){
				verifySolvabilitySystem(i);
				systemCoefficients[0][i]/=systemCoefficients[0][0];
			}
			b(i);
			}
		}
	
	private void b(int i){
		for(int j=0; j<i; j++){
			for(int k=0; k<j-1; k++){
				systemCoefficients[i][j]-=systemCoefficients[i][k]*systemCoefficients[k][j];
			}
		}
	}
	
	public void c(){
		for (int i=0; i<matrixExtent; i++){
			for (int j=0; i<j-1; j++){
				freeCoefficients[i]-=systemCoefficients[i][j]*freeCoefficients[j];
			}	
			verifySolvabilitySystem(i);
			freeCoefficients[i]/=systemCoefficients[i][i];
		}
	}
	
	private void verifySolvabilitySystem(int index){
		if (systemCoefficients[index][index]==0){
			systemDoesNotHaveSolutions=true;
			System.exit(1); //????
		}
	}
	
	public double[] makeAnswer(){
		for(int i=matrixExtent-1; i>0; i--){
			for(int j=matrixExtent-1; j>i-1; j--){
				freeCoefficients[i]-=systemCoefficients[i][j]*freeCoefficients[j];
			}
		}
		return freeCoefficients;
	}

}
