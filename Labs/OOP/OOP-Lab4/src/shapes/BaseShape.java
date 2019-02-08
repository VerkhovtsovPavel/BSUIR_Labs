package shapes;

import java.awt.Graphics;
import java.io.Serializable;

public abstract class BaseShape implements Serializable {

	private static final long serialVersionUID = -3648631254968967601L;
	protected int xStart;
	protected int yStart;

	public BaseShape(int xCoordinateStart, int yCoordinateStart) {
		this.xStart = xCoordinateStart;
		this.yStart = yCoordinateStart;
	}

	public abstract void draw(Graphics graph);
}
