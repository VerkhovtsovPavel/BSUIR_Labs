package by.bsuir.verkpavel.saimmod.graphics;

import java.util.ArrayList;

import by.bsuir.verkpavel.saimmod.graphics.ui.Histogram;

public class HistogramBuilder {
    private static HistogramBuilder instance;

    public static HistogramBuilder getInstance() {
        if (instance == null) {
            instance = new HistogramBuilder();
        }
        return instance;
    }
    
    public void buildHistogram(ArrayList<Double> distribution, int sectionCount){
        double[] histogramData = new double[distribution.size()];
        
        for(int i=0; i< distribution.size(); i++)
            histogramData[i] = distribution.get(i);  
        
        
        double[] minMax = foundMaxAndMin(distribution);
        double max = minMax[1];
        double min = minMax[0];
         
       new Histogram(histogramData, sectionCount, min ,max);
    }
    
    private double[] foundMaxAndMin(ArrayList<Double> distribution){
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        
        for(double value : distribution)
        {
            if(value < min){
                min = value;
            }
            if(value>max){
                max = value;
            }
        }
        return new double[]{min, max};
    }
}
