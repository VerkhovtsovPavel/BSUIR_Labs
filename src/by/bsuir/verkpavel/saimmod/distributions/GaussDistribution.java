package by.bsuir.verkpavel.saimmod.distributions;

public class GaussDistribution extends BaseDistribution{
	private float initMathExperction;
	private float initSigma;
	private BaseDistribution generator;

	public GaussDistribution(int a, int b,float mathExp, float sigma, int length) {
		super(length);
		this.initMathExperction = mathExp;
		this.initSigma = sigma;
		this.generator = new UniformDistribution(a,b, 6*length);
		generator.build();
	}

	@Override
	public void build() {
		for(int i=0; i<this.count; i++){
			float sum =0;
			for(int j=0; j<6; j++){
				sum+=(generator.items.get(6*i+j)-3);
			}
			items.add((float) (initMathExperction+initSigma*Math.sqrt(2)*sum));
		}
	}

	@Override
	public void introduce() {
		System.out.println(String.format(
                "Gauss distribution with parameters mathExpection = %f, sigma = %f", this.initMathExperction, this.initSigma));		
	}

}
