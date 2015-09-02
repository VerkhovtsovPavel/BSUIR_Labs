package by.bsiur.verkpavel.saimmod.distributions;

import java.util.ArrayList;

public abstract class BaseDistribution {
    private ArrayList<Float> items;

    private float mathematicalExpectation;
    private float dispersion;
    private float sigma;

    private float period;

    public BaseDistribution() {
        this.items = new ArrayList<Float>();
        this.mathematicalExpectation = Float.NaN;
        this.dispersion = Float.NaN;
        this.sigma = Float.NaN;
        this.period = Float.NaN;
    }

    public abstract void build();

    public float getMathExpectation() {
        if (this.mathematicalExpectation == Float.NaN) {
            this.mathematicalExpectation = calculateMathExpectation();
        }
        return this.mathematicalExpectation;
    }

    public float getDispersion() {
        if (this.dispersion == Float.NaN) {
            this.dispersion = calculateDispersion();
        }
        return this.dispersion;
    }

    public float getSigma() {
        if (this.sigma == Float.NaN) {
            this.sigma = calculateSigma();
        }
        return this.sigma;
    }

    public float getPeriod() {
        if (this.period == Float.NaN) {
            this.period = calculatePeriod();
        }
        return this.period;
    }
//>>>> Maybe move to separate class
    private float calculateMathExpectation() {
        float sum = 0;
        for (float item : items) {
            sum += item;
        }
        return sum / items.size();
    }

    private float calculateDispersion() {
        float sumOfDeviation = 0;

        for (float item : items) {
            sumOfDeviation += Math.pow(item - getMathExpectation(), 2);
        }
        return sumOfDeviation / items.size();
    }

    private float calculateSigma() {
        // TODO Check formula
        return (float) Math.sqrt(getDispersion());
    }

    private float calculatePeriod() {
        // TODO Auto-generated method stub
        return 0;
    }
//>>>>>>>>>    
}
