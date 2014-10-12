package main;

public class IntegratorVersion2 {
	
	private int startX;
	private int endX;
	private double h;
	private int n;
	private PolynomLejandra polynom;
	
public IntegratorVersion2(int a, int b, int n){
	this.startX = a;
    this.endX = b;
    this.h = (double)(b - a) / 2 / n;
    this.n = n;
    this.polynom = new PolynomLejandra();
}
	
	        public double computeIntegralGauss()
	        {
	            double resultFX = 0;
	            for (int i = 0; i < n; i++)
	            {
	                resultFX = resultFX + Main.integrableFunction((startX + endX) / 2 + (endX - startX) / 2 * polynom.coefficients[n - 2].x[i]) * 
	                           polynom.coefficients[n - 2].a[i];
	            }
	            return resultFX * (endX - startX) / 2;
	        }
	      
	 
	 

	        public double computeIntegralSimpson()
	        {
	            double tempFX = 0, resultFX = 0, x = 0;
	            resultFX = Main.integrableFunction(startX) + Main.integrableFunction(endX);
	            x = startX + h;
	            for (int i = 0; i < n; i++)
	            {
	                tempFX = tempFX + 4 * (Main.integrableFunction(x));
	                x += 2 * h;
	            }
	            x = startX + 2 * h;
	            for (int i = 0; i < n - 1; i++)
	            {
	                tempFX = tempFX + 2 * (Main.integrableFunction(x));
	                x += 2 * h;
	            }
	            return (resultFX + tempFX) * h / 3;
	        }

	    

}
