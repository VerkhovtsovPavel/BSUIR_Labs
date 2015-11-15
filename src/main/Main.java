package main;

import signals.Drawable;
import signals.PolyharmonicsSignal;
import signals.SimpleSignal;
import spectrum.AmplitudeSpectrum;
import spectrum.PhaseSpectrum;

//TODO Use table of sin and cos
public class Main {
    private static int N = 1024;
    
    public static void main(String[] args){
        SimpleSignal originalTestSignal = new SimpleSignal(10, Math.PI/2, 1, N);
        originalTestSignal.buildGraph("Test Signal (Original)");
        
        AmplitudeSpectrum amplitudeTestSpec = new AmplitudeSpectrum(originalTestSignal);
        amplitudeTestSpec.buildGraph("Amplityde Spectrum of Test Signal");
        
        PhaseSpectrum phaseTestSpec = new PhaseSpectrum(originalTestSignal);
        phaseTestSpec.buildGraph("Phase Spectrum of Test Signal");
        
        Drawable reconstructedTestSignal = new SimpleSignal(amplitudeTestSpec, phaseTestSpec, N);
        reconstructedTestSignal.buildGraph("Test Signal (Reconstructed)");
        
        PolyharmonicsSignal originalPolySignal = new PolyharmonicsSignal(N);
        originalPolySignal.buildGraph("Polyharmonics Signal (Original)");
        
        AmplitudeSpectrum amplitudePolySpec = new AmplitudeSpectrum(originalPolySignal);
        amplitudePolySpec.buildGraph("Amplityde Spectrum of Polysignal");
        
        PhaseSpectrum phasePolySpec = new PhaseSpectrum(originalPolySignal);
        phasePolySpec.buildGraph("Phase Spectrum of Polysignal");
        
        PolyharmonicsSignal reconstructedPolySignalWithInitialPhase = new PolyharmonicsSignal(amplitudePolySpec, phasePolySpec,false, N);
        reconstructedPolySignalWithInitialPhase.buildGraph("Polyharmonics Signal with initial phase (Reconstructed)");
        
        PolyharmonicsSignal reconstructedPolySignalWithoutInitialPhase = new PolyharmonicsSignal(amplitudePolySpec, phasePolySpec,true, N);
        reconstructedPolySignalWithoutInitialPhase.buildGraph("Polyharmonics Signal without initial phase (Reconstructed)");
    }
}
