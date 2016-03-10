package by.bsuir.verkpavel.saimmod.distributions;

public class ExponensialDistribution extends BaseDistribution {
	private double lambda;
	private BaseDistribution generator;
	public ExponensialDistribution(double lambda, int length) {
		super(length);
		this.lambda = lambda;
		this.generator = new LemerDistribution(10, 476754345, 1, length);
	}

	@Override
	public void build() {
		generator.build();
		for(double item : generator.items){
			items.add((double)(-1/(double)this.lambda*Math.log(1-item)));
		}

	}

	@Override
	public void introduce() {
		System.out.println(String.format(
                "Exponensial distribution with parameters lambda = %f", this.lambda));		


	}

}
