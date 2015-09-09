package by.bsuir.verkpavel.saimmod.distributions;


public class UniformDistribution extends BaseDistribution {
	private double startInterval;
	private double endInterval;
	private BaseDistribution baseDistribution;

	public UniformDistribution(double a, double b, int length) {
		super(length);
		this.startInterval = a;
		this.endInterval = b;
		this.baseDistribution = new LemerDistribution(3, 69712349, 1, length);
		
	}

	@Override
	public void build() {
		double intervalLength = endInterval - startInterval;
		baseDistribution.build();
		for(double item : baseDistribution.items){
			items.add(startInterval + intervalLength*item);
		}

	}

	@Override
	public void introduce() {
		System.out.println(String.format(
                "Uniform distribution with parameters a = %d, b = %d", this.startInterval, this.endInterval));

	}

}
