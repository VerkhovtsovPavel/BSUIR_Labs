package main;

import random.SignalRandomParameters;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
    	
       for(int i=0; i<2000; i++){
    	   System.out.println(SignalRandomParameters.getRandomAmplitude());
       }

    }

}
