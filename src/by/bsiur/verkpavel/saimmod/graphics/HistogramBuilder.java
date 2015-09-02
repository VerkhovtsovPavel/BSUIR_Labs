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
        HistogramWindow.create(null);
    }
    
    private float[] foundMaxAndMin(ArrayList<Float> distribution){
        return null;
    }
    
    private int[] calculateOccurrence(ArrayList<Float> distribution){
        return null;
        
        //int[(currentValue-min)/((max-min)/sectionCount)]++
    }
}
