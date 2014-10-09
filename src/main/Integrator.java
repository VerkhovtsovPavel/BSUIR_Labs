package main;

public class Integrator {
	
	private int highInterval;
	private int lowInterval;
	private int aPlusBdev2;
	private int bMinusAdev2;
	private final int numberOfIntervals = 7;
	
	public Integrator(int hInterval, int lInterval) {
		this.highInterval = hInterval;
		this.lowInterval = lInterval;
		this.aPlusBdev2 = (lInterval+hInterval)/2;
		this.bMinusAdev2 = (hInterval - lInterval)/2;
	}
	
	public double integrateByGauss() {
		return bMinusAdev2 *(Main.integrableFunction(makeNegativeArgument()) + Main.integrableFunction(makePositiveArgument()));	
	}
	
	public double integrateBySimpson(){
		 double sum=0,sum2=0;
	     double[] x=new double[numberOfIntervals];
	     double h=(double)(highInterval-lowInterval)/numberOfIntervals;
	     for(int i=1;i<numberOfIntervals;i++){
	        x[i]=lowInterval+i*h;
	    }
	     for(int i=0;i<numberOfIntervals;i++){
	         sum+=Main.integrableFunction(x[i]);
	        sum2+=Main.integrableFunction((x[i-1]+x[i])/2);
	     }
	     return h/6*(Main.integrableFunction(lowInterval)+Main.integrableFunction(highInterval)+2*sum+4*(sum2+highInterval));

	}
	
	private double makeNegativeArgument(){
		return (aPlusBdev2 - bMinusAdev2/Math.sqrt(3));
	}
	
	private double makePositiveArgument(){
		return (aPlusBdev2 + bMinusAdev2/Math.sqrt(3));
	}


}
