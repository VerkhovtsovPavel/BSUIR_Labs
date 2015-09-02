package by.bsiur.verkpavel.saimmod.distributions;

public class UniformDistribution extends BaseDistribution {

    private int a;
    private int m;
    private int r0;

    public UniformDistribution(int a, int m, int r0) {
        super();
        this.a = a;
        this.m = m;
        this.r0 = r0;
    }

    @Override
    public void build() {
        float r = r0;
        for (int i = 0; i < count; i++) {
            r = (a * r % m) / m;
            items.add(r);
        }
    }

    @Override
    public void consoleInfo() {
        System.out.println(String.format(
                "Uniform distribution with parameters a = %d, m = %d, R0 = %d", a, m, r0));
        super.consoleInfo();
    }
}
