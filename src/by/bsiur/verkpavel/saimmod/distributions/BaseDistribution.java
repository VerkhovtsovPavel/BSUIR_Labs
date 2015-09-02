package by.bsiur.verkpavel.saimmod.distributions;

import java.util.ArrayList;

import by.bsiur.verkpavel.saimmod.calculation.ProbabilityTheoryCalculator;
import by.bsiur.verkpavel.saimmod.graphics.HistogramBuilder;

public abstract class BaseDistribution {
    private final static float e = 0.00001f;
    protected final int count = 500_000;

    protected ArrayList<Float> items;
    
    private ProbabilityTheoryCalculator ptCalculator;

    private float mathematicalExpectation;
    private float dispersion;
    private float sigma;

    private float period;
    private float unperiodicitySegment;

    public BaseDistribution() {
        this.ptCalculator = new ProbabilityTheoryCalculator();
        this.items = new ArrayList<Float>();
        this.mathematicalExpectation = Float.NaN;
        this.dispersion = Float.NaN;
        this.sigma = Float.NaN;
        this.period = Float.NaN;
        this.unperiodicitySegment = Float.NaN;
    }

    public abstract void build();

    public void consoleInfo() {
        System.out.println("Math expectation = " + getMathExpectation());
        System.out.println("Dispersion = " + getDispersion());
        System.out.println("Sigma = " + getSigma());
        System.out.println("Checking by indect sign = " + checkByIndectSign());
        System.out.println("Period" + (getPeriod() != 0 ? " = " + getPeriod() : " > " + count));
        System.out.println("Unperiodicity segment"
                + (getUnperiodicitySegment() != 0 ? " = " + getUnperiodicitySegment() : " > "
                        + count));
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
        return (2 * countOfPars / items.size() - Math.PI / 2) < e;
    }

    private boolean checkNaN(float value) {
        return Float.isNaN(value);
    }

    public void drawHistogram() {
        HistogramBuilder.getInstance().buildHistogram(items, 20);
    }

}