package shapes;

import java.awt.Graphics;
import java.util.ArrayList;

public class Line extends BaseShape {

	private static final long serialVersionUID = -4511594268059967779L;
	private int xFinish;
	private int yFinish;

	public Line(ArrayList<Integer> parametersList) {
		super(parametersList.get(0), parametersList.get(1));
		this.xFinish = parametersList.get(2);
		this.yFinish = parametersList.get(3);
	}

	@Override
	public void draw(Graphics graph) {
		graph.drawLine(xStart, yStart, xFinish, yFinish);
	}

	@Override
	public ArrayList<Integer> getParams() {
		ArrayList<Integer> params = new ArrayList<Integer>();
		params.add(xStart);
		params.add(yStart);
		params.add(xFinish);
		params.add(yFinish);
		return params;
	}
	
	
}
