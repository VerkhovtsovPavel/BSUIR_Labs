package shapes;

import java.awt.Graphics;
import java.io.Serializable;

public abstract class BaseShape implements Serializable{

	protected int xStart;
	protected int yStart;
	
	public BaseShape(int xCoordinateStart, int yCoordinateStart){
		this.xStart = xCoordinateStart;
		this.yStart = yCoordinateStart;
	}	

	public void draw(Graphics graph){};
}
