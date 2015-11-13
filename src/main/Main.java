package main;

import signals.Drawable;
import signals.PolyharmonicsSignal;
import signals.SimpleSignal;

public class Main {
    private static int N = 1024;
    
    public static void main(String[] args){
        Drawable testSignal = new SimpleSignal(10, Math.PI/2, 1, N);
        testSignal.buildGraph("Test Signal (Original)");
        
        Drawable polySignal = new PolyharmonicsSignal(N);
        polySignal.buildGraph("Polyharmonics Signal (Original)");
   
    }
}
