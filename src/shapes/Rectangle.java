package shapes;

import java.awt.Graphics;
import java.util.ArrayList;

public class Rectangle extends BaseShape {

	private static final long serialVersionUID = -1379338493391382916L;
	private int width;
	private int height;

	public Rectangle(ArrayList<Integer> parametersList) {
		super(parametersList.get(0), parametersList.get(1));
		this.width = Math.abs(parametersList.get(0) - parametersList.get(2));
		this.height = Math.abs(parametersList.get(1) - parametersList.get(3));
	}

	public void draw(Graphics graph) {
		graph.drawRect(xStart, yStart, width, height);
	}

}
