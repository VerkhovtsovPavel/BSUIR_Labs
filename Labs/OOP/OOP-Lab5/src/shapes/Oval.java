package shapes;

import java.awt.Graphics;
import java.util.ArrayList;

public class Oval extends BaseShape {

	private static final long serialVersionUID = 9020713477310806826L;
	private int width;
	private int height;

	public Oval(ArrayList<Integer> parametersList) {
		super(parametersList.get(0), parametersList.get(1));
		this.width = Math.abs(parametersList.get(0) - parametersList.get(2));
		this.height = Math.abs(parametersList.get(1) - parametersList.get(3));
	}

	@Override
	public void draw(Graphics graph) {
		graph.drawOval(xStart, yStart, width, height);
	}

	@Override
	public ArrayList<Integer> getParams() {
		ArrayList<Integer> params = new ArrayList<Integer>();
		params.add(xStart);
		params.add(yStart);
		params.add(xStart + width);
		params.add(yStart + height);
		return params;
	}

}
