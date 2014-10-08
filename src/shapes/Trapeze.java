package shapes;

import java.awt.Graphics;
import java.util.ArrayList;

public class Trapeze extends BaseShape {
	private static final long serialVersionUID = -49959130419783484L;
	private int width;
	private int xFinish;
	private int yFinish;

	public Trapeze(ArrayList<Integer> parametersList) {
		super(parametersList.get(0), parametersList.get(1));
		this.xFinish = parametersList.get(2);
		this.yFinish = parametersList.get(3);
		this.width = Math.abs(parametersList.get(0) - parametersList.get(2)) / 2;
	}

	public void draw(Graphics graph) {
		graph.drawLine(xStart, yStart, xFinish - width, yStart);
		graph.drawLine(xFinish - width, yStart, xFinish, yFinish);
		graph.drawLine(xFinish, yFinish, xStart - width, yFinish);
		graph.drawLine(xStart - width, yFinish, xStart, yStart);
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