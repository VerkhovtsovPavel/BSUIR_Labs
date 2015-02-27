package all;

import org.apache.log4j.Logger;

public class Dock extends Thread {
	private Logger logger = Logger.getLogger(Dock.class);

	private boolean isWorkInUnloadMode;
	private Port port;

	public Dock(Port port) {
		this.port = port;
		this.isWorkInUnloadMode = true;
	}

	public void unloadShips() {
		logger.info("Change work mode to unload");
		this.isWorkInUnloadMode = true;
	}

	public void loadShips() {
		logger.info("Change work mode to load");
		this.isWorkInUnloadMode = false;
	}

	// TODO Add checks
	@Override
	public void run() {
		while (!port.isShipsQueueEmpty()) {
			Ship currentShip = null;
			if (isWorkInUnloadMode) {
				logger.info("Get ship to unload");
				currentShip = port.getShipOnUnload();
				if (currentShip != null) {
					port.load += currentShip.getLoad();
					logger.info("Ship loaded " + currentShip.getLoad() + " port loaded " + port.load);
				}
			} else {
				logger.info("Get ship to load");
				currentShip = port.getShipOnLoad();
				if (currentShip != null) {
					port.load -= currentShip.getCapacity();
					logger.info("Ship capacity " + currentShip.getCapacity() + "ship loaded " + currentShip.getLoad() + " port loaded " + port.load);
				}
			}
		}
	}
}
