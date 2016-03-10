package shapes;

import java.awt.Graphics;
import java.util.ArrayList;

public class Oval extends BaseShape{
	
	private int width;
	private int height;

	public Oval(ArrayList<Integer> parametersList) {
		super(parametersList.get(0), parametersList.get(1));
		this.width=Math.abs(parametersList.get(0)-parametersList.get(2));
		this.height=Math.abs(parametersList.get(1)-parametersList.get(3));
	}
	
	public void draw(Graphics graph){
		graph.drawOval(xStart, yStart, width, height);
	}
	

}
