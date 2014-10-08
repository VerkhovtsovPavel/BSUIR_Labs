package shapes;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class BaseShape implements Serializable {

	private static final long serialVersionUID = -3648631254968967601L;
	protected int xStart;
	protected int yStart;

	public BaseShape(int xCoordinateStart, int yCoordinateStart) {
		this.xStart = xCoordinateStart;
		this.yStart = yCoordinateStart;
	}

	public abstract void draw(Graphics graph);
	public abstract ArrayList<Integer> getParams();
	
	public String getShapeName(){
		String className = this.getClass().toString();
		return className.substring(className.lastIndexOf(".")+1);
	}
}
