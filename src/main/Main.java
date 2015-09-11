package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import signals.ChangesAmplitudeInitialPhaseFrequencySignal;
import signals.ChangesInTheInitialPhaseSignal;

//Rename functions
public class Main {
    private static Date bornDate; 
    private static int groupNumber = 2;
    private static int N = 2048;
    private static double initialPhase = Math.PI/3;
    public static void main(String[] args) throws ParseException{
        bornDate = new SimpleDateFormat("dd/mm/yyyy").parse("08/06/1993");
        int frequency = groupNumber%7+7;
        int amplitude = (int) (bornDate.getTime()%7+7);
        ChangesInTheInitialPhaseSignal changesInTheInitialPhase = new ChangesInTheInitialPhaseSignal(amplitude, frequency, initialPhase, N);
        changesInTheInitialPhase.buildGraph(5, "Part 1");
        
       ChangesAmplitudeInitialPhaseFrequencySignal changesAmplitudeInitialPhaseFrequency = new ChangesAmplitudeInitialPhaseFrequencySignal(amplitude, frequency, initialPhase, N);
       changesAmplitudeInitialPhaseFrequency.buildGraph(5, "Part 2 (All)");
       changesAmplitudeInitialPhaseFrequency.buildGraphOfSum(5);
    }
}
