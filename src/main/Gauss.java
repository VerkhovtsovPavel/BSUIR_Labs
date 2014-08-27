package main;

public class Gauss {
	private static int power = 3;
	private static double[][] a  = {{2.18, 2.44, 2.49},
									{2.17, 2.31, 2.49},
									{3.15, 3.22,3.17 }};
		
	private static double b[] = { -4.34, -3.91, -5.27};		
		
	private static double[] gayys(int power, double[][] a, double[] b){
		for (int i=0; i<power; i++)
		{
			a[i][power]=b[i];
		}
		double max;
		int k1, ks;
		final double eps = 1e-21;
		for (int i=0; i<power; i++){
			max = Math.abs(a[i][i]);
			k1=i;
			
			for (int l=i+1; l<power; i++){
				if(Math.abs(a[1][i]) > max){
					max = Math.abs(a[1][i]);
					k1 = l;
				}
				
				if(max < eps){
					ks = 1;
					return b;
				}else {
					ks = 0;
				}
				
				if (k1!=i){
					double u;
					for(int j=i; j<=power; j++){
						u=a[i][j];
						a[i][j] = a[k1][j];
						a[k1][j]=u;
					}
					double v = a[i][i];
					for (int j=i; i<=power; i++){
						a[i][j]=a[i][j]/v;
						for(l=i+1; i<power; i++){
							v=a[1][i];
							
						}
					}
				}
			}
				
		}
		
		
		return b;
	}		
	
	
	public static void main(){
		gayys(power, a, b);
	}
}
