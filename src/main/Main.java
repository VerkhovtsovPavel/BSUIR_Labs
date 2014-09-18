package main;

import java.util.Scanner;

import drawing.Painter;
import figure.Line;
import figure.Oval;
import figure.Rectangle;

public class Main {	
	
	public enum Figure {

	    Rectangle("RequestOne"),
	    Oval("RequestTwo"),
	    Line("RequestThree");
	    
	    private String typeValue;
	    
	    private Figure(String type) {
	        typeValue = type;
	    }
	     
	    static public Figure getType(String pType) {
	            for (Figure type: Figure.values()) {
	                if (type.getTypeValue().equals(pType)) {
	                    return type;
	                }
	            }
	            throw new RuntimeException("unknown type");
	        }
	        
	        public String getTypeValue() {
	            return typeValue;
	        }     
	    }

	private static String commandLine = "";

	public static void main(String[] args){
		Rectangle rectangle = new Rectangle(10,10,50,50);
		Line line = new Line(100,100, 500, 500);
		Oval oval = new Oval(200, 200, 250, 250);

		System.out.println("Figure drawing shell");

		while(!commandLine.equals("Exit")){
			commandLine = new Scanner(System.in).next();

			if (commandLine.equals("Draw")){
				Painter.drawObjects();
			}

			if (takeWord(commandLine).equals("Add")){
				String figure = takeWord(commandLine);
				int[] param = parseParam(figure);
				switch (Figure.valueOf(figure)){
					case Rectangle:
						Painter.addObjectInList(new Rectangle(param[0], param[1], param[2], param[3]));
						break;

					case Oval:
						Painter.addObjectInList(new Oval(param[0], param[1], param[2], param[3]));
						break;

					case Line:
						Painter.addObjectInList(new Line(param[0], param[1], param[2], param[3]));
						break;
					default:
						System.out.println("Not correct commant");
				}

				//Painter.addObjectInList();
			}
			
			//Add Rectangle 17 85 95 35
			//Draw
		}

		Painter.addObjectInList(rectangle);
		Painter.addObjectInList(line);
		Painter.addObjectInList(oval);
		Painter.drawObjects();
	}
	
	private static int[] parseParam(String paramString){
		int[] paramArray = new int[4];
		for(int i=0; i<4; i++){
			paramArray[i]=Integer.valueOf(takeWord(paramString));
		}
		return paramArray;
	}
	
	private static String takeWord(String str){
		String word = str.substring(0,str.indexOf(" "));
		str.substring(str.indexOf(" ")+1);
		return word;
	}
}
