package by.bsiur.verkpavel.saimmod.distributions;

public class UniformDistribution extends BaseDistribution {
    private final static float eps = 0.1f;
    
    private int a;
    private int m;
    private int r0;
    
    private float period;
    private float unperiodicitySegment;

    public UniformDistribution(int a, int m, int r0, int length) {
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

    @Override
    public void consoleInfo() {
        System.out.println(String.format(
                "Uniform distribution with parameters a = %d, m = %d, R0 = %d", a, m, r0));
        super.consoleInfo();
        Object[] indectSign = checkByIndectSign();
        System.out.println("Checking by indect sign = " + indectSign[0]+" ("+indectSign[1]+")");
        System.out.println("Period" + (getPeriod() != 0 ? " = " + getPeriod() : " > " + count));
        System.out.println("Unperiodicity segment"
                + (getUnperiodicitySegment() != 0 ? " = " + getUnperiodicitySegment() : " > "
                        + count));
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

    public Object[] checkByIndectSign() {
        int countOfPars = 0;

        for (int i = 0; i < items.size() / 2; i++){
            if (Math.pow(items.get(2 * i), 2) + Math.pow(items.get(2 * i + 1), 2) < 1) {
                countOfPars++;
            }
        }
        float result = (float)2 * countOfPars / items.size();
        return new Object[]{Math.abs(result - Math.PI / 4) < eps, result};
    }
}
