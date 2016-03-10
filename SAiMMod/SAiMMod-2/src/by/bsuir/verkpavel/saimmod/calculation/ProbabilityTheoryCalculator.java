package by.bsuir.verkpavel.saimmod.calculation;

import java.util.ArrayList;

public class ProbabilityTheoryCalculator {
    
    public double calculateMathExpectation(ArrayList<Double> items) {
        double sum = 0;
        for (double item : items) {
            sum += item;
        }
        return sum / items.size();
    }

    public double calculateDispersion(ArrayList<Double> items, double mathExpectation) {
        double sumOfDeviation = 0;

        for (double item : items) {
            sumOfDeviation += Math.pow(item - mathExpectation, 2);
        }
        return sumOfDeviation / items.size();
    }

    public double calculateSigma(double dispersion) {
        return (double) Math.sqrt(dispersion);
    }

    public int calculatePeriod(ArrayList<Double> items) {
        try {
            double ac = items.get(items.size() - 1);
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

    public int calculateUnperiodicitySegment(ArrayList<Double> items, int period) {
        int i = 0;

        while (!items.get(i).equals(items.get(i + period))) {
            i++;
        }
        return i + period;
    }
}
