package port;

import java.util.PriorityQueue;
import java.util.Queue;

import org.apache.log4j.Logger;

import entity.Ship;

public class Port {
	private Logger logger = Logger.getLogger(Port.class);

	private Dock[] docks;
	private final long capacity;
	private volatile long load;
	private Queue<Ship> shipsToUnload = new PriorityQueue<>();
	private Queue<Ship> shipsToLoad = new PriorityQueue<>();

	private boolean stopWork;

	public synchronized boolean incrementLoad(int count) {
		if(load + count <capacity){
			this.load += count;
			return true;
		}
		return false;
	}

	public synchronized boolean decrementLoad(int count) {
		if (load > count) {
			this.load -= count;
			return true;
		}

		return false;
	}

	public synchronized long getLoad() {
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

		while (!isDocksStopWork()) {
			docksControls();
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		waitAllDocks();
		logger.info("Port stop working");
	}

	private void waitAllDocks() {
		for (Dock dock : docks) {
			try {
				dock.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private void docksControls() {
		if (shipsToLoad.isEmpty()) {
			logger.info("100% docks work in unload mode");
			changeDocksWorkType(0);
		} else if (shipsToUnload.isEmpty()) {
			logger.info("100% docks work in load mode");
			changeDocksWorkType(docks.length);
		} else {
			if (this.load > this.capacity / 4 * 3) {
				logger.info("25% docks work in unload mode and 75% in load mode");
				changeDocksWorkType(docks.length / 4 * 3);
			} else if (this.load > this.capacity / 2) {
				logger.info("50% docks work in unload mode and 50% in load mode");
				changeDocksWorkType(docks.length / 2);
			} else if (this.load > this.capacity / 4) {
				logger.info("75% docks work in unload mode and 25% in load mode");
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

	public void setStopWork() {
		this.stopWork = true;
	}

	public boolean isDocksStopWork() {
		return shipsToLoad.isEmpty() && shipsToUnload.isEmpty() && stopWork;
	}

	public long getCapacity() {
		return capacity;
	}
}
