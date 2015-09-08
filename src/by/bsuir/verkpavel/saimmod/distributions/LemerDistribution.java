package by.bsuir.verkpavel.saimmod.distributions;

public class LemerDistribution extends BaseDistribution {
    private final static float eps = 0.00001f;
    
    private int a;
    private int m;
    private int r0;
    
    private float period;
    private float unperiodicitySegment;

    public LemerDistribution(int a, int m, int r0, int length) {
        super(length);
        this.a = a;
        this.m = m;
        this.r0 = r0;
        
        this.period = Float.NaN;
        this.unperiodicitySegment = Float.NaN;
    }

    @Override
    public void build() {
        float r = r0;
        for (int i = 0; i < count; i++) {
            r = (a * r % m);
            items.add(r/m);
        }
    }
    
    public int getPeriod() {
        if (checkNaN(this.period)) {
            this.period = ptCalculator.calculatePeriod(items);
        }
        return (int) this.period;
    }

    public int getUnperiodicitySegment() {
        if (checkNaN(this.unperiodicitySegment)) {
            this.unperiodicitySegment = ptCalculator.calculateUnperiodicitySegment(items, getPeriod());
        }
        return (int) this.unperiodicitySegment;

    }

    public boolean checkByIndectSign() {
        int countOfPars = 0;

        for (int i = 0; i < items.size() / 2; i++){
            if (Math.pow(items.get(2 * i), 2) + Math.pow(items.get(2 * i + 1), 2) < 1) {
                countOfPars++;
            }
        }
        return (2 * countOfPars / items.size() - Math.PI / 2) < eps;
    }

	@Override
	public void introduce() {
		System.out.println(String.format(
                "Lemer distribution with parameters a = %d, m = %d, R0 = %d", a, m, r0));
	}

	@Override
	public void specialInfo() {
		System.out.println("Checking by indect sign = " + checkByIndectSign());
        System.out.println("Period" + (getPeriod() != 0 ? " = " + getPeriod() : " > " + count));
        System.out.println("Unperiodicity segment"
                + (getUnperiodicitySegment() != 0 ? " = " + getUnperiodicitySegment() : " > "
                        + count));
	}
	
	
}
