package main;

import signals.ChangesAmplitudeInitialPhaseFrequencySignal;
import signals.ChangesInTheInitialPhaseSignal;

//Rename functions
public class Main {
    private static int bornDate = 8; 
    private static int groupNumber = 2;
    private static int N = 1024;
    
    public static void main(String[] args){
        int initialPhase = groupNumber%7+7;
        int amplitude = (int) bornDate%7+7;
        ChangesInTheInitialPhaseSignal changesInTheInitialPhase = new ChangesInTheInitialPhaseSignal(amplitude,  initialPhase, N);
        changesInTheInitialPhase.buildGraph(3, "Part 1");
        
       ChangesAmplitudeInitialPhaseFrequencySignal changesAmplitudeInitialPhaseFrequency = new ChangesAmplitudeInitialPhaseFrequencySignal(amplitude, initialPhase, N);
       changesAmplitudeInitialPhaseFrequency.buildGraph(3, "Part 2 (All)");
       changesAmplitudeInitialPhaseFrequency.buildGraphOfSum(5);
    }
}
