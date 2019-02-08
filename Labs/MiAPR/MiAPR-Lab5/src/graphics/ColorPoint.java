package graphics;

import java.awt.Color;
import java.awt.Point;

public class ColorPoint {
	public Point location;
	public Color color;
	public int size;
	
	public ColorPoint(Point point, Color color, int radius) {
		location =point;
		this.color=color;
		size = radius;
	}
}
