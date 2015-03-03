package all;

import java.util.PriorityQueue;
import java.util.Queue;

import org.apache.log4j.Logger;

public class Port {
	private Logger logger = Logger.getLogger(Port.class);

	private Dock[] docks;
	private final long capacity;
	private volatile long load;
	private Queue<Ship> shipsToUnload = new PriorityQueue<>();
	private Queue<Ship> shipsToLoad = new PriorityQueue<>();

	public synchronized void incrementLoad(int count){
		this.load+=count;
	}
	
	public synchronized void decrementLoad(int count){
		this.load-=count;
	}
	
	public synchronized long getLoad(){
		return this.load;
	}
	
	public Port(long capacity, int numberOfDocks) {
		this.capacity = capacity;
		this.docks = new Dock[numberOfDocks];
		initialazeDocks();
	}

	private void initialazeDocks() {
		for (int i = 0; i < docks.length; i++) {
			docks[i] = new Dock(this);
		}
	}

	public void startDocksWorking() {
		for (Dock dock : docks) {
			dock.start();
		}

		while (!isShipsQueueEmpty()) {
			docksControls();
			Thread.yield();
		}
		logger.info("Port stop working");
	}

	public boolean isShipsQueueEmpty() {
		return shipsToLoad.isEmpty() && shipsToUnload.isEmpty();
	}

	private void docksControls() {
		if (shipsToLoad.isEmpty()) {
			changeDocksWorkType(0);
		} else if (shipsToUnload.isEmpty()) {
			changeDocksWorkType(docks.length);
		} else {
			if (this.load > this.capacity / 4 * 3) {
				changeDocksWorkType(docks.length / 4 * 3);
			} else if (this.load > this.capacity / 2) {
				changeDocksWorkType(docks.length / 2);
			} else if (this.load > this.capacity / 4) {
				changeDocksWorkType(docks.length / 4);
			}
		}

	}

	private void changeDocksWorkType(int dockWorkingInLoadMode) {
		for (int i = 0; i < dockWorkingInLoadMode; i++) {
			docks[i].loadShips();
		}
		for (int i = dockWorkingInLoadMode; i < docks.length; i++) {
			docks[i].unloadShips();
		}
	}

	public synchronized Ship getShipOnLoad() {
		return shipsToLoad.poll();
	}

	public synchronized Ship getShipOnUnload() {
		return shipsToUnload.poll();
	}

	public synchronized void addShipOnLoad(Ship ship) {
		shipsToLoad.offer(ship);
	}

	public synchronized void addShipOnUnload(Ship ship) {
		shipsToUnload.offer(ship);
	}
}
