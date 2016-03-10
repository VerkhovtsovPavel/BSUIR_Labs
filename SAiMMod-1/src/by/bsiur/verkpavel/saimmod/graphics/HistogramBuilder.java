package by.bsiur.verkpavel.saimmod.graphics;

import java.util.ArrayList;

import by.bsiur.verkpavel.saimmod.graphics.ui.Histogram;

public class HistogramBuilder {
    private static HistogramBuilder instance;

    public static HistogramBuilder getInstance() {
        if (instance == null) {
            instance = new HistogramBuilder();
        }
        return instance;
    }
    
    public void buildHistogram(ArrayList<Float> distribution, int sectionCount){
        double[] histogramData = new double[distribution.size()];
        
        for(int i=0; i< distribution.size(); i++)
            histogramData[i] = distribution.get(i);  
        
        
        float[] minMax = foundMaxAndMin(distribution);
        float max = minMax[1];
        float min = minMax[0];
         
       new Histogram(histogramData, sectionCount, min ,max);
    }
    
    private float[] foundMaxAndMin(ArrayList<Float> distribution){
        float min = Float.MAX_VALUE;
        float max = Float.MIN_VALUE;
        
        for(float value : distribution)
        {
            if(value < min){
                min = value;
            }
            if(value>max){
                max = value;
            }
        }
        return new float[]{min, max};
    }
}
