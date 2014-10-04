package main;

public class Integrator {
	
	private int highInterval;
	private int lowInterval;
	private int aPlusBdev2;
	private int bMinusAdev2;
	
	public Integrator(int hInterval, int lInterval) {
		this.highInterval = hInterval;
		this.lowInterval = lInterval;
		this.aPlusBdev2 = (lInterval+hInterval)/2;
		this.bMinusAdev2 = (hInterval - lInterval)/2;
	}
	
	public double integrateByGauss(){
		return bMinusAdev2 *(Main.integrableFunction(makeNegativeArgument()) + Main.integrableFunction(makePositiveArgument()));
		
	}
	
	public double integrateBySimpson(){
		double firstSummand = Main.integrableFunction(lowInterval) + Main.integrableFunction(highInterval);
		double secondSummand = 4*Main.integrableFunction(aPlusBdev2);
		return (double)bMinusAdev2/3*(firstSummand+secondSummand);
		
	}
	
	private double makeNegativeArgument(){
		return (aPlusBdev2 - bMinusAdev2/Math.sqrt(3));
	}
	
	private double makePositiveArgument(){
		return (aPlusBdev2 + bMinusAdev2/Math.sqrt(3));
	}


}
