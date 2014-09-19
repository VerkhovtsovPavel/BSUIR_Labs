package shapes;

import java.awt.Graphics;

public class Line extends BaseShape{
	
	private int xFinish;
	private int yFinish;

	public Line(int xCoordinateStart, int yCoordinateStart, int xCoordinateFinish, int yCoordinateFinish){
		super(xCoordinateStart, yCoordinateStart);
		this.xFinish = xCoordinateFinish;
		this.yFinish = yCoordinateFinish;
	}
	
	public void draw(Graphics graph){
		graph.drawLine(xStart, yStart, xFinish, yFinish);
	}
}
