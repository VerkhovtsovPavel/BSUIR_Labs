package approximation;

public class Lagrange {
	private double[] arrayX;
	private double[] arrayY;
	private double point;
	private double l = 0;
	


	public Lagrange(double[] xArray, double[] yArray, double p){ // TODO Normal names of variable (p,l,point,s)
		arrayX=xArray;
		arrayY=yArray;
		point = p;
	}
	
	public double approximazeFunction(){
		for(int i=0; i<arrayX.length; i++){
			double s=1;
			for(int j=0; j<arrayY.length; j++){
				if (i!=j){
					s*=(point-arrayX[j])/(arrayX[i]-arrayX[j]);
				}
			}
			l+=arrayY[i]*s;
		}
		return l;
	}
}	