package shapes;

import java.awt.Graphics;

public abstract class BaseShape {

	protected int xStart;
	protected int yStart;
	
	public BaseShape(int xCoordinateStart, int yCoordinateStart){
		this.xStart = xCoordinateStart;
		this.yStart = yCoordinateStart;
	}	

	public void draw(Graphics graph){};
}
