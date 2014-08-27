package Matrix;

public class Matrix {
	private int power = 3;
	private double[][] systemCoefficients  = {{2.18, 2.44, 2.49},
											  {2.17, 2.31, 2.49},
											  {3.15, 3.22,3.17 },
											  {-4.34, -3.91, -5.27}};
	
	
	//	private static double freeCoefficients[] = { -4.34, -3.91, -5.27};
	
	
	
	
	public Matrix(int p, double[][] a, double[] b){
		this.power = p;
		this.systemCoefficients = a;
		this.systemCoefficients[power]= b;
	}
	
	public void swapRow(int rowNumber){
		double buffer;
		int maxIndex = getIndexOfMaxElement(rowNumber); 
		for(int j=rowNumber; j<=power; j++){
			buffer=systemCoefficients[rowNumber][j];
			systemCoefficients[rowNumber][j] = systemCoefficients[maxIndex][j];
			systemCoefficients[maxIndex][j]=buffer;
		}	
	}
		
	private int getIndexOfMaxElement(int rowNumber){
	    double max = Math.abs(systemCoefficients[rowNumber][rowNumber]);
		int maxIndex = rowNumber;
		for (int i=rowNumber+1; i<power; i++){
			if(Math.abs(systemCoefficients[i][rowNumber]) > max){
				max = Math.abs(systemCoefficients[i][rowNumber]);
				maxIndex = i;
			}
		}
		
		//
		return maxIndex;	
	}
	
}
