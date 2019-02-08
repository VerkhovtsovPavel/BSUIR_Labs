package by.bsuir.verkpavel.saimmod.distributions;

public class SimpsonDistribution extends BaseDistribution{
    private double startInterval;
    private double endInterval;
    private BaseDistribution firstGenerator;
    private BaseDistribution secondGenerator;
    
    public SimpsonDistribution(double a, double b, int length) {
        super(length);
        this.startInterval = a;
        this.endInterval = b;
        
        this.firstGenerator = new LemerDistribution(3, 69712349, 1, length);
        this.secondGenerator = new LemerDistribution(3, 4397149, 1, length);
        
        this.firstGenerator.build();
        this.secondGenerator.build();
    }

    @Override
    public void build() {
        double intervalLength = endInterval/2 - startInterval/2;
        for(int i=0; i< firstGenerator.items.size(); i++){
            items.add(startInterval/2+intervalLength*firstGenerator.items.get(i)+ startInterval/2+intervalLength*secondGenerator.items.get(i));
        }
        
    }

    @Override
    public void introduce() {
        System.out.println(String.format(
                "Simpson distribution with parameters a = %f, b = %f", this.startInterval, this.endInterval));
        
    }
}
