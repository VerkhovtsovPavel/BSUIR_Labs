package all;

import java.util.Random;

public class Sea {
	private static Random seaPower = new Random();

	public static void generateShips(Port port, int numberOfShips) {
		for (int i = 0; i < numberOfShips; i++) {
			if (seaPower.nextBoolean()) {
				port.addShipOnLoad(new Ship(0, seaPower.nextInt(1000) + 100));
			} else {
				int load = seaPower.nextInt(900) + 100;
				port.addShipOnUnload(new Ship(load, load + seaPower.nextInt(100)));
			}
		}
	}
}
