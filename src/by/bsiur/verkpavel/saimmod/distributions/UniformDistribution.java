package by.bsiur.verkpavel.saimmod.distributions;

public class UniformDistribution extends BaseDistribution{

    private int a;
    private int m;
    private int r0;
    
   public UniformDistribution(int a, int m, int r0) {
        super();
        this.a =a;
        this.m =m;
        this.r0 = r0;
    }
    
    @Override
    public void build() {
        int r = r0;
    }

}
