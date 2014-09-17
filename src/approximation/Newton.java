package approximation;

public class Newton extends BaseApproximation{
	
	private int countInterPolNides;

	/**
	 * Constructor
	 * @param xArray Array of x coordinate
	 * @param yArray Array of y coordinate
	 */
	public Newton(final double[] xArray, final double[] yArray){
		super(xArray, yArray);
		this.countInterPolNides = xArray.length;
	}
	
	@Override
	public double approximazeFunction(final double x){
		double[] xArray = this.arrayX.clone();
		double[] yArray = this.arrayY.clone();
		double l=yArray[0];
		double p=1;

		for (int k=1; k<countInterPolNides; k++){
			p=p*(x-xArray[k-1]);
			for (int i=0; i<(countInterPolNides-k); i++){
				yArray[i]=(yArray[i+1]-yArray[i])/(xArray[i+k]-xArray[i]);
			}
			l=l+p*yArray[0];
		}
		return l;

    }

	//TODO Remove i,j,k in cycle.
	//TODO Rename variables F, den, res
	
}
