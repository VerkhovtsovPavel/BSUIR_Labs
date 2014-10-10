package main;

public class Integrator {

	private int highInterval;
	private int lowInterval;
	private int aPlusBdev2;
	private int bMinusAdev2;
	private final int numberOfIntervals = 128;

	public Integrator(int hInterval, int lInterval) {
		this.highInterval = hInterval;
		this.lowInterval = lInterval;
		this.aPlusBdev2 = (lInterval + hInterval) / 2;
		this.bMinusAdev2 = (hInterval - lInterval) / 2;
	}

	public double integrateByGauss() {
		double c = Math.sqrt((double) 3 / 5);
		double h1 = (double) (highInterval - lowInterval) / 2;
		double c1 = c * h1;
		double x2 = (double) (highInterval + lowInterval) / 2;
		double f1 = Main.integrableFunction(x2 - c1);
		double f3 = Main.integrableFunction(x2 + c1);
		double s1 = h1 * (5 * f1 + 8 * Main.integrableFunction(x2) + 5 * f3)
				/ 9;
		int n = 2;
		double h;
		double s2 = 0;
		double d;
		do {
			h = (double)(highInterval - lowInterval) / n;
			h1 = h / 2;
			c1 = c * h1;
			x2 = lowInterval + h1;
			double x1 = x2 - c1;
			double x3 = x2 + c1;

			for (int i = 1; i < n; i++) {
				s2 = s2 + 5 * Main.integrableFunction(x1) + 8
						* Main.integrableFunction(x2) + 5
						* Main.integrableFunction(x3);
				x1 = x1 + h;
				x2 = x2 + h;
				x3 = x3 + h;
			}
			s2 = s2 * h1 / 9;
			d = Math.abs(s1 - s2) / 63;
			s1 = s2;
			n = 2 * n;
		} while (d < h);

		System.out.print(n);
		return s2;

		// return bMinusAdev2 *(Main.integrableFunction(makeNegativeArgument())
		// + Main.integrableFunction(makePositiveArgument()));
	}

	public double integrateBySimpson() {
		/*double step = (double) (highInterval - lowInterval) / numberOfIntervals;
		double doubleSum = 0, quadSum = 0;
		for (int i = 1; i < numberOfIntervals; i++) {
			doubleSum += Main.integrableFunction(lowInterval + i * step);
			quadSum += Main.integrableFunction(lowInterval +  i*step
					/ 2);
		}
		return (step / 6)
				* (Main.integrableFunction(lowInterval)
						+ Main.integrableFunction(highInterval) + 2 * doubleSum + 4 * quadSum);*/
		
		double f0,f1,s,s1,s2,h,x1,x2,d;
	    int	n;

		f0=Main.integrableFunction(lowInterval);
		f1=Main.integrableFunction(highInterval);
		s=f0-f1;
		s1=(highInterval-lowInterval)*(f0+f1+4*Main.integrableFunction((lowInterval+highInterval)/2))/6;
		n=2;
		do
		{
		h=(double)(highInterval-lowInterval)/n;
		x1=lowInterval+h/2;
		x2=lowInterval+h;
		s2=s;
		for(int i=1; i< n; i++){
		s2=s2+4*Main.integrableFunction(x1)+2*Main.integrableFunction(x2);
		x1=x1+h;
		x2=x2+h;
		}
		s2=s2*h/6;
		d=Math.abs(s1-s2)/15;
		s1=s2;
		n=n*2;
		}
		while( d<h);
		System.out.print(n);
		return s1;

	}

	private double makeNegativeArgument() {
		return (aPlusBdev2 - bMinusAdev2 / Math.sqrt(3));
	}

	private double makePositiveArgument() {
		return (aPlusBdev2 + bMinusAdev2 / Math.sqrt(3));
	}

}
