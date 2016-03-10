package figure;

import java.awt.Graphics;

public class Oval extends BaseFigure{
	
	private int width;
	private int height;

	public Oval(int xCoordinateStart, int yCoordinateStart, int xCoordinateFinish, int yCoordinateFinish) {
		super(xCoordinateStart, yCoordinateStart);
		this.width=Math.abs(xCoordinateStart-xCoordinateFinish);
		this.height=Math.abs(yCoordinateStart-yCoordinateFinish);
	}
	
	public void draw(Graphics graph){
		graph.drawOval(xStart, yStart, width, height);
	}
	

}
