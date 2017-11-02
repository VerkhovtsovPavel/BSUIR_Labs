package spectrum;

import graph.LineChart;
import signals.Drawable;

import java.util.ArrayList;

public abstract class Spectrum extends Drawable {

	private ArrayList<Double> signalValues;
	
	protected ArrayList<Double> Ac;
	protected ArrayList<Double> As;

	public Spectrum(ArrayList<Double> signalValues) {
		super(signalValues.size());
		this.signalValues = signalValues;
		calculateAcAndAs();
	}
	
	@Override
    public void buildGraph(String title){
        LineChart lineChart = new LineChart(title);
        lineChart.addDataset(calculateData(), 1, 1);
        lineChart.draw("bar");
    }

	private void calculateAcAndAs() {
		Ac = new ArrayList<Double>();
		As = new ArrayList<Double>();
		for (int j = 0; j < getN() / 2 - 1; j++) {
			Ac.add(calculateAcj(j));
			As.add(calculateAsj(j));
		}
	}

	private double calculateAsj(int j) {
		double sum = 0;
		for (int i = 0; i < signalValues.size(); i++) {
			sum += signalValues.get(i) * Math.sin(2 * Math.PI * j * i / getN());
		}
		return sum * 2 / getN();
	}

	private double calculateAcj(int j) {
		double sum = 0;
		for (int i = 0; i < signalValues.size(); i++) {
			sum += signalValues.get(i) * Math.cos(2 * Math.PI * j * i / getN());
		}
		return sum * 2 / getN();
	}
}
