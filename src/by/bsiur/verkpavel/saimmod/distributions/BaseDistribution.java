package by.bsiur.verkpavel.saimmod.distributions;

import java.util.ArrayList;

import by.bsiur.verkpavel.saimmod.calculation.ProbabilityTheoryCalculator;
import by.bsiur.verkpavel.saimmod.graphics.HistogramBuilder;

public abstract class BaseDistribution {
    protected int count;

    protected ArrayList<Float> items;
    protected ProbabilityTheoryCalculator ptCalculator;

    private float mathematicalExpectation;
    private float dispersion;
    private float sigma;

    public BaseDistribution(int length) {
        this.ptCalculator = new ProbabilityTheoryCalculator();
        this.items = new ArrayList<Float>();
        this.mathematicalExpectation = Float.NaN;
        this.dispersion = Float.NaN;
        this.sigma = Float.NaN;
        this.count = length;
    }

    public abstract void build();

    public void consoleInfo() {
        System.out.println("Math expectation = " + getMathExpectation());
        System.out.println("Dispersion = " + getDispersion());
        System.out.println("Sigma = " + getSigma());
    }

    public float getMathExpectation() {
        if (checkNaN(this.mathematicalExpectation)) {
            this.mathematicalExpectation = ptCalculator.calculateMathExpectation(this.items);
        }
        return this.mathematicalExpectation;
    }

    public float getDispersion() {
        if (checkNaN(this.dispersion)) {
            this.dispersion = ptCalculator.calculateDispersion(items, getMathExpectation());
        }
        return this.dispersion;
    }

    public float getSigma() {
        if (checkNaN(this.sigma)) {
            this.sigma = ptCalculator.calculateSigma(getDispersion());
        }
        return this.sigma;
    }

    protected boolean checkNaN(float value) {
        return Float.isNaN(value);
    }

    public void drawHistogram() {
        HistogramBuilder.getInstance().buildHistogram(items, 20);
    }

}