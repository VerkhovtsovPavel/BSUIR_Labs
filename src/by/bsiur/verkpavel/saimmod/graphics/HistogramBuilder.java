package by.bsiur.verkpavel.saimmod.graphics;

import java.util.ArrayList;

import by.bsiur.verkpavel.saimmod.graphics.ui.HistogramWindow;

public class HistogramBuilder {
    private static HistogramBuilder instance;

    public static HistogramBuilder getInstance() {
        if (instance == null) {
            instance = new HistogramBuilder();
        }
        return instance;
    }
    
    public void buildHistogram(ArrayList<Float> distribution, int sectionCount){
        HistogramWindow.create(calculateOccurrence(distribution, sectionCount));
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
    
    private float[] calculateOccurrence(ArrayList<Float> distribution, int sectionCount){
        float[] minMax = foundMaxAndMin(distribution);
        float max = minMax[1];
        float min = minMax[0];
        float sectionInterval = ((max-min)/sectionCount)+0.0001f;
        float[] histogramData = new float[sectionCount];
        
        for(float currentValue : distribution)
            histogramData[(int) ((currentValue-min)/sectionInterval)]++;
        
        for(int i=0; i< histogramData.length; i++)
            histogramData[i]/=distribution.size();
        
        return histogramData;       
    }
}
