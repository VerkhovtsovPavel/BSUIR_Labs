package main;

import Matrix.Matrix;

public class Gauss {
	private static int power = 3;
	private static double[][] a  = {{2.18, 2.44, 2.49, -4.34},
									{2.17, 2.31, 2.49, -3.91},
									{3.15, 3.22, 3.17, -5.27}};
		
	private static double b[] = { -4.34, -3.91, -5.27};		

	public static void main(String args[]){
		Matrix matrix = new Matrix(); 
		for (int i=0; i<power/*not good*/; i++){
			matrix.swapRow(i);
			matrix.divideRow(i);
			matrix.subtractMatrix(i);
		}
		double[] answer = matrix.makeanswer();
		for(int i=0; i<answer.length; i++)
			System.out.println(answer[i]);
	}
}
