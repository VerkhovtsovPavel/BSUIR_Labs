package main;

import drawing.Painter;
import figure.Line;
import figure.Oval;
import figure.Rectangle;

public class Main {	
	
	public static void main(String[] args){
		Rectangle rectangle = new Rectangle(10,10,50,50);
		Line line = new Line(100,100, 500, 500);
		Oval oval = new Oval(200, 200, 250, 250);
		Painter.addObjectInList(rectangle);
		Painter.addObjectInList(line);
		Painter.addObjectInList(oval);
		Painter.drawObjects();
	}
}
