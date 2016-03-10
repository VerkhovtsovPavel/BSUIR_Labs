package signals;

import java.util.ArrayList;
import java.util.Random;

public class SignalWithNoize extends Drawable {
	private int amplitudeB1;
	private int amplitudeB2;

	private ArrayList<Double> values;
	private double[] noizeArray;

	public SignalWithNoize(int amplitudeB1, int amplitudeB2, int N) {
		super(N);
		this.amplitudeB1 = amplitudeB1;
		this.amplitudeB2 = amplitudeB2;
		fillNoizeArray();
	}

	private void fillNoizeArray() {
		Random noize = new Random();
		noizeArray = new double[20];
		for (int i = 0; i < 20; i++) {
			noizeArray[i] = noize.nextInt(2);
		}

	}

	@Override
	protected ArrayList<Double> calculateData() {
		values = new ArrayList<Double>();
		for (int i = 0; i < getN(); i++) {
			values.add(amplitudeB1 * Math.sin(2 * Math.PI * i / getN())
					+ calculateNoizeCompanent(i));
		}
		return values;
	}

	private Double calculateNoizeCompanent(int i) {
		Random noize = new Random();
		double value = 0;
		for (int j = 50; j < 70; j++) {
			value += Math.pow(-1, noize.nextInt(2)) * this.amplitudeB2
					* Math.sin(2 * Math.PI * i * j / getN());
		}
		return value;
	}

	@Override
	public ArrayList<Double> getValues() {
		return values;
	}

}
