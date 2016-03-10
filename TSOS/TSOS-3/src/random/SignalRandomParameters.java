package random;

import java.util.Random;

public class SignalRandomParameters {
	private static final int[] amplitudes = { 1, 3, 5, 8, 10, 12, 16 };
	private static final double[] initialPhases = { Math.PI / 6, Math.PI / 4,
			Math.PI / 3, Math.PI / 2, 3 * Math.PI / 4, Math.PI };
	
	private static Random rand = new Random();
	
	
	public static int getRandomAmplitude(){
		return amplitudes[rand.nextInt(amplitudes.length)];
	}
	
	public static double getRandomInitialPhase(){
		return initialPhases[rand.nextInt(initialPhases.length)];
	}
}
