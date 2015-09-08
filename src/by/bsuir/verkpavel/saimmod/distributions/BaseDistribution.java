package by.bsuir.verkpavel.saimmod.distributions;

import java.util.ArrayList;

import by.bsuir.verkpavel.saimmod.calculation.ProbabilityTheoryCalculator;
import by.bsuir.verkpavel.saimmod.graphics.HistogramBuilder;

public abstract class BaseDistribution {
    protected int count = 1_000_000;

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
    public abstract void introduce();

    public final void consoleInfo() {
    	introduce();
        System.out.println("Math expectation = " + getMathExpectation());
        System.out.println("Dispersion = " + getDispersion());
        System.out.println("Sigma = " + getSigma());
        specialInfo();
    }

    public void specialInfo() {
		
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