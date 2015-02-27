package all;

import java.util.PriorityQueue;
import java.util.Queue;

public class Port {
	private Dock[] docks;
	private final long capacity;
	public volatile long load;
	private Queue<Ship> shipsToUnload = new PriorityQueue<Ship>();
	private Queue<Ship> shipsToLoad = new PriorityQueue<Ship>();
	
	
	public Port (long capacity, int numberOfDocks){
		this.capacity = capacity;
		this.docks = new Dock[numberOfDocks];
	}
	
	private void docksControls(){
		if(this.load > this.capacity/4*3){
			changeDocksWorkType(docks.length/4*3);
		}
		else if(this.load > this.capacity/2){
			changeDocksWorkType(docks.length/2);
		}
		else if(this.load > this.capacity/4){
			changeDocksWorkType(docks.length/4);
		}
	}

	private void changeDocksWorkType(int dockWorkingInLoadMode) {
		for(int i=0; i<dockWorkingInLoadMode; i++){
			docks[i].loadShips();
		}
		for(int i = dockWorkingInLoadMode; i<docks.length; i++){
			docks[i].unloadShips();
		}
	}
	
	public synchronized Ship getShipOnLoad(){
		return shipsToLoad.poll();
	}
	
	public synchronized Ship getShipOnUnload(){
		return shipsToUnload.poll();
	}
}
