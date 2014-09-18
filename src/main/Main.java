package main;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import drawing.Painter;
import figure.BaseFigure;

public class Main {	
	
	private static String commandLine = "";

	public static void main(String[] args) throws Exception{
		System.out.println("Figure drawing shell");

		while(!commandLine.equals("Exit")){
			commandLine = new Scanner(System.in).nextLine();

			if (commandLine.equals("Draw")){
				Painter.drawObjects();
			}

			else if (takeWord().equals("Add")){
				String figureName = takeWord();
				int[] param = parseParam();
				
				Class<?> figureClass = Class.forName("figure."+figureName);
				Constructor<?> figureConstructor = figureClass.getDeclaredConstructors()[0];
				BaseFigure figure = (BaseFigure) figureConstructor.newInstance(param[0], param[1], param[2], param[3]);
				Painter.addObjectInList(figure);
			}
		}
	}
	// TODO Implement List of parameters 
	private static int[] parseParam(){
		int[] paramArray = new int[4];
		for(int i=0; i<3; i++){
			paramArray[i]=Integer.valueOf(takeWord());
		}
		
		paramArray[3]=Integer.valueOf(commandLine);
		return paramArray;
	}
	
	private static String takeWord(){
		String word = commandLine.substring(0,commandLine.indexOf(" "));
		commandLine=commandLine.substring(commandLine.indexOf(" ")+1);
		return word;
	}
}
