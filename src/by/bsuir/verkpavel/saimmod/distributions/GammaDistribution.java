package by.bsuir.verkpavel.saimmod.distributions;

public class GammaDistribution extends BaseDistribution {
	private double lambda;
	private int setLength;
	private BaseDistribution generator;
	
	public GammaDistribution(double lambda, int  n, int length) {
		super(length);
		this.lambda = lambda;
		this.setLength = n;
		this.generator = new LemerDistribution(3, 69712349, 1, n*length);
		this.generator.build();
	}

	@Override
	public void build() {
		for(int i=0; i<this.count; i++){
			double sum =1;
			for(int j=0; j<this.setLength; j++){
				sum*=generator.items.get(this.setLength*i+j);
			}
			items.add((double) (-1/this.lambda*Math.log(sum)));
		}

	}

	@Override
	public void introduce() {
	}	

}
