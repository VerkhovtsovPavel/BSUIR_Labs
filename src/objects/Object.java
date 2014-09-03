package objects;

/**
 * Class describing object with two characteristics x and y coordinates.
 * @author Pavel_Verkhovtsov
 */
public class Object {
	private int xCoordinate;
	private int yCoordinate;

	private int areaNumber;
	private double distanceToAllPoints;

	/**
	 * Constructor.
	 * @param x Coordinate x
	 * @param y Coordinate y
	 */
 	public Object(final int x, final int y){
 		xCoordinate = x;
 		yCoordinate = y;
 		// TODO Object number
	}
 	/**
 	 * Choice area(class).
 	 * @param centers Array of class centers
 	 */
 	public void choiceArea(final Object[] centers){
 		double minDistance = Double.MAX_VALUE;
 		for (int i=0; i< centers.length; i++){
 			double distance = Math.sqrt(Math.pow(Math.abs(xCoordinate-centers[i].getX()),2)+Math.pow(Math.abs(yCoordinate-centers[i].getY()),2));
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
}
