package port;

import org.apache.log4j.Logger;

import entity.Ship;

public class Dock extends Thread {
	private Logger logger = Logger.getLogger(Dock.class);

	private static final String SHIP_PORT_LOAD = "Ship %s, port loaded  = %d";

	private boolean isWorkInUnloadMode;
	private Port port;

	public Dock(Port port) {
		this.port = port;
		this.isWorkInUnloadMode = true;
	}

	public void unloadShips() {
		logger.debug("Set work mode to unload");
		this.isWorkInUnloadMode = true;
	}

	public void loadShips() {
		logger.debug("Set work mode to load");
		this.isWorkInUnloadMode = false;
	}

	@Override
	public void run() {
		while (!port.isDocksStopWork()) {
			Ship currentShip = null;
			if (isWorkInUnloadMode) {
				currentShip = port.getShipOnUnload();
				if (currentShip != null) {
					logger.info("Get ship to unload with load " + currentShip.getLoad());
					if (port.incrementLoad(currentShip.getLoad())) {
						logger.info(String.format(SHIP_PORT_LOAD, "unloaded", port.getLoad()));
					} else {
						logger.info("Dock can't unload this ship now");
						if (currentShip.addCounter()) {
							logger.warn("Can't process ship exceeded exceeded the maximum number of attempts");
						} else {
							port.addShipOnUnload(currentShip);
						}
						loadShips();
						Thread.yield();
					}
				} else {
					logger.debug("Unload queue in empty");
					loadShips();
					Thread.yield();
				}
			} else {
				currentShip = port.getShipOnLoad();
				if (currentShip != null) {
					logger.info("Get ship to load with capacity " + currentShip.getCapacity());
					if (port.decrementLoad(currentShip.getCapacity())) {
						logger.info(String.format(SHIP_PORT_LOAD, "loaded", port.getLoad()));
					} else {
						logger.info("Dock can't load this ship now");
						if (currentShip.addCounter()) {
							logger.warn("Can't process ship exceeded exceeded the maximum number of attempts");
						} else {
							port.addShipOnLoad(currentShip);
						}
						unloadShips();
						Thread.yield();
					}

				} else {
					logger.debug("Load queue in empty");
					unloadShips();
					Thread.yield();
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
