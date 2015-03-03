package all;

import org.apache.log4j.Logger;

public class Main {
	private static Logger logger = Logger.getLogger(Main.class);
	
	
	public static void main(String[] args){
		logger.info("Start program");
		logger.info("Create port");
		Port port = new Port(1000000, 20);
		Sea.generateShips(port, 100000);
		port.startDocksWorking();
	}
}
