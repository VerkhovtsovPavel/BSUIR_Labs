package main;

import approximation.MedianFiltering;
import approximation.ParaboloidFourthDegree;
import approximation.SlidingAverage;
import signals.Drawable;
import signals.SignalWithNoize;
import spectrum.AmplitudeSpectrum;

//TODO Use table of sin and cos
public class Main {
	private static int N = 1024;

	public static void main(String[] args) {
		Drawable signalWithNoize = new SignalWithNoize(100, 1, N);
		signalWithNoize.buildGraph("Signal with noize");
		
		AmplitudeSpectrum amplitudeSpectrum = new AmplitudeSpectrum(signalWithNoize);
		amplitudeSpectrum.buildGraph("Spectrum signal with noize");
		
		ParaboloidFourthDegree paraboloidFourthDegree = new ParaboloidFourthDegree(signalWithNoize);
		paraboloidFourthDegree.buildGraph("Approximation by Paraboloid Fourth Degree");
		
		MedianFiltering medianFiltering = new MedianFiltering(signalWithNoize, 7);
		medianFiltering.buildGraph("Approximation by Median Filtering");
		
		SlidingAverage slidingAverage = new SlidingAverage(signalWithNoize, 5);
		slidingAverage.buildGraph("Approximation by Sliding Average");
		
		AmplitudeSpectrum amplitudeSpectrum2 = new AmplitudeSpectrum(slidingAverage);
		amplitudeSpectrum2.buildGraph("Approximation signal spectrum");
	}
}
