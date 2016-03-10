package port;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;

import entity.Ship;

public class Port {
	private Logger logger = Logger.getLogger(Port.class);

	private Dock[] docks;
	private final long capacity;
	private AtomicLong load;
	private BlockingQueue<Ship> shipsToUnload = new LinkedBlockingQueue<>();
	private BlockingQueue<Ship> shipsToLoad = new LinkedBlockingQueue<>();

	private boolean stopWork;

	public boolean incrementLoad(int count) {
		if(load.get() + count < capacity){
			this.load.addAndGet(count);
			return true;
		}
		return false;
	}

	public boolean decrementLoad(int count) {
		if (load.get() > count) {
			this.load.set(load.get()- count) ;
			return true;
		}
		return false;
	}

	public long getLoad() {
		return this.load.get();
	}

	public Port(long capacity, int numberOfDocks) {
		this.capacity = capacity;
		this.docks = new Dock[numberOfDocks];
		this.load = new AtomicLong();
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
			if (this.load.get() > this.capacity / 4 * 3) {
				logger.info("25% docks work in unload mode and 75% in load mode");
				changeDocksWorkType(docks.length / 4 * 3);
			} else if (this.load.get() > this.capacity / 2) {
				logger.info("50% docks work in unload mode and 50% in load mode");
				changeDocksWorkType(docks.length / 2);
			} else if (this.load.get() > this.capacity / 4) {
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

	public Ship getShipOnLoad() {
		return shipsToLoad.poll();
	}

	public Ship getShipOnUnload() {
		return shipsToUnload.poll();
	}

	public void addShipOnLoad(Ship ship) {
		shipsToLoad.offer(ship);
	}

	public void addShipOnUnload(Ship ship) {
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
