package objects;

public class Object {
	private int xCoordinate;
	private int yCoordinate;
	
	private int areaNumber;
	private double distanceToAllPoints;
	
	
 	public Object(int x, int y){
 		xCoordinate = x;
 		yCoordinate = y;
	}
 	
 	public void choiceArea(Object[] centers){
 		double minDistance = 100;//TODO set max double.
 		for (int i=0; i< centers.length; i++){
 			int[] centerCoordinate = centers[i].getCoordinates();
 			double distance = Math.sqrt(Math.pow(Math.abs(xCoordinate-centerCoordinate[0]),2)+Math.pow(Math.abs(yCoordinate-centerCoordinate[1]),2));
 			if (distance<minDistance){
 				minDistance = distance;
 				areaNumber = i;
 			}
 		}
 	}
 	
 	public int[] getCoordinates(){
 		int[] result =  {xCoordinate, yCoordinate};
 		return result;
 	}
}
