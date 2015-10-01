package other;

import java.util.Random;

public class TimePeriodGenerator {
    private double intens;
    private Random generator;

    public TimePeriodGenerator(double intens) {
        this.intens = intens;
        this.generator = new Random();
    }

    public int getInterval() {
        return (int) (-1/(double)this.intens*Math.log(1-generator.nextDouble())*500);
    }


}
