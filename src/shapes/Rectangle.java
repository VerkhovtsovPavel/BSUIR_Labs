package shapes;

import java.awt.Graphics;

public class Rectangle extends BaseShape{
	
	private int width;
	private int height;

	public Rectangle(int xCoordinateStart, int yCoordinateStart, int xCoordinateFinish, int yCoordinateFinish) {
		super(xCoordinateStart, yCoordinateStart);
		this.width=Math.abs(xCoordinateStart-xCoordinateFinish);
		this.height=Math.abs(yCoordinateStart-yCoordinateFinish);
	}
	
	public void draw(Graphics graph){
		graph.drawRect(xStart, yStart, width, height);
	}

}
