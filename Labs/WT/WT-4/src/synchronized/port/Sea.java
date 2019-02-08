package port;

import java.util.Random;

import org.apache.log4j.Logger;

import entity.Ship;

public class Sea extends Thread {
	private Logger logger = Logger.getLogger(Sea.class);
	private Random seaMagicPower = new Random();
	private Port port;
	private int numberOfShips;

	public Sea(Port port, int numberOfShips) {
		this.port = port;
		this.numberOfShips = numberOfShips;
	}

	@Override
	public void run() {
		for (int i = 0; i < numberOfShips; i++) {
			if (seaMagicPower.nextBoolean()) {
				logger.info("Add ship on load");
				port.addShipOnLoad(new Ship(0, seaMagicPower.nextInt(1000) + 100));
			} else {
				logger.info("Add ship on unload");
				int load = seaMagicPower.nextInt(1000) + 100;
				port.addShipOnUnload(new Ship(load, load));
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		port.setStopWork();
	}
}
