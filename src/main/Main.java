package main;

import signals.ChangesAmplitudeInitialPhaseFrequencySignal;

//Rename functions
public class Main {
    private static int bornDate = 8; 
    private static int groupNumber = 2;
    private static int N = 4096;
    
    public static void main(String[] args){
        int initialPhase = groupNumber%7+7;
        int amplitude = (int) bornDate%7+7;
        
       ChangesAmplitudeInitialPhaseFrequencySignal changesAmplitudeInitialPhaseFrequency = new ChangesAmplitudeInitialPhaseFrequencySignal(amplitude, initialPhase, N);
       changesAmplitudeInitialPhaseFrequency.buildGraphOfSum(5);
       System.out.println("Math expection> "+changesAmplitudeInitialPhaseFrequency.getMathExpection());
    }
}
