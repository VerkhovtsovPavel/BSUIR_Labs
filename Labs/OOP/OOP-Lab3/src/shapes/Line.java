package shapes;

import java.awt.Graphics;
import java.util.ArrayList;

public class Line extends BaseShape{
	
	private int xFinish;
	private int yFinish;

	public Line(ArrayList <Integer> parametersList){
		super(parametersList.get(0), parametersList.get(1));
		this.xFinish = parametersList.get(2);
		this.yFinish = parametersList.get(3);
	}
	
	public void draw(Graphics graph){
		graph.drawLine(xStart, yStart, xFinish, yFinish);
	}
}
