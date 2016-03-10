package objects;

import java.util.ArrayList;

/**
 * Class describing object with two characteristics x and y coordinates.
 * @author Pavel_Verkhovtsov
 */
public class Object {
	private int xCoordinate;
	private int yCoordinate;

	private int areaNumber;
	private int objectIndex;

	/**
	 * Constructor.
	 * @param x Coordinate x
	 * @param y Coordinate y
	 * @param index Number in object array
	 */
 	public Object(final int x, final int y, final int index){
 		xCoordinate = x;
 		yCoordinate = y;
 		objectIndex = index;
	}
 	/**
 	 * Choice area(class).
 	 * @param centers Array of class centers
 	 */
 	public void choiceArea(final ArrayList<Object> centers){
 		double minDistance = Double.MAX_VALUE;
 		for (int i=0; i< centers.size(); i++){
 			double distance = Math.sqrt(Math.pow(Math.abs(xCoordinate-centers.get(i).getX()),2)+Math.pow(Math.abs(yCoordinate-centers.get(i).getY()),2));
 			if (distance<minDistance){
 				minDistance = distance;
 				areaNumber = i;
 			}
 		}
 	}

 	/**
 	 * Getter for x coordinate.
 	 * @return x coordinate object's
 	 */
 	public int getX(){
 		return xCoordinate;
 	}

 	/**
 	 * Getter for y coordinate.
 	 * @return y coordinate object's
 	 */
 	public int getY(){
 		return yCoordinate;
 	}

 	/**
 	 * Getter for area number
 	 * @return area number
 	 */
 	public int getAreaNumber(){
 		return areaNumber;
 	}

 	public int getIndex(){
 		return objectIndex;
 	}
}
