package figure;

import java.awt.Graphics;

public abstract class BaseFigure {

	protected int xStart;
	protected int yStart;
	
	public BaseFigure(int xCoordinateStart, int yCoordinateStart){
		this.xStart = xCoordinateStart;
		this.yStart = yCoordinateStart;
	}	

	public void draw(Graphics graph){};
}
