package by.bsiur.verkpavel.saimmod.calculation;

import java.util.ArrayList;

public class ProbabilityTheoryCalculator {
    
    public float calculateMathExpectation(ArrayList<Float> items) {
        float sum = 0;
        for (float item : items) {
            sum += item;
        }
        return sum / items.size();
    }

    public float calculateDispersion(ArrayList<Float> items, float mathExpectation) {
        float sumOfDeviation = 0;

        for (float item : items) {
            sumOfDeviation += Math.pow(item - mathExpectation, 2);
        }
        return sumOfDeviation / items.size();
    }

    public float calculateSigma(float dispersion) {
        return (float) Math.sqrt(dispersion);
    }

    public int calculatePeriod(ArrayList<Float> items) {
        try {
            float ac = items.get(items.size() - 1);
            int i = 0;

            while (!items.get(i).equals(ac)) {
                i++;
            }

            int firstFound = i;
            i++;
            while (!items.get(i).equals(ac)) {
                i++;
            }
            int secondFound = i;

            return secondFound - firstFound;
        } catch (IndexOutOfBoundsException e) {
            return 0;
            // TODO Make something
        }
    }

    public int calculateUnperiodicitySegment(ArrayList<Float> items, int period) {
        int i = 0;

        while (!items.get(i).equals(items.get(i + period))) {
            i++;
        }
        return i + period;
    }
}
