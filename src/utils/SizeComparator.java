package utils;

import java.util.Comparator;
import algorithms.PlotterSupporter;

public class SizeComparator implements Comparator<PlotterSupporter> {

	@Override
	public int compare(PlotterSupporter firstPlotterSupporter, PlotterSupporter secondPlotterSupporter) {
		return firstPlotterSupporter.getIngredients().size() - secondPlotterSupporter.getIngredients().size();
	}
}
