package by.bsuir.verkpavel.saimmod.distributions;

public class GaussDistribution extends BaseDistribution{
	private double initMathExperction;
	private double initSigma;
	private BaseDistribution generator;

	public GaussDistribution(double mathExp, double sigma, int length) {
		super(length);
		this.initMathExperction = mathExp;
		this.initSigma = sigma;
		this.generator = new LemerDistribution(3, 69712349, 1, 6 * length);
		generator.build();
	}

	@Override
	public void build() {
		for(int i=0; i<this.count; i++){
			double sum =0;
			for(int j=0; j<6; j++){
				sum+=(generator.items.get(6*i+j));
			}
			items.add((double) (initMathExperction+initSigma*Math.sqrt(2)*(sum-3)));
		}
	}

	@Override
	public void introduce() {
		System.out.println(String.format(
                "Gauss distribution with parameters mathExpection = %f, sigma = %f", this.initMathExperction, this.initSigma));		
	}

}
