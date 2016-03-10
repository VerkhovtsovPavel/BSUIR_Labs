package main;

import org.apache.log4j.Logger;

import port.Port;
import port.Sea;

public class Main {
	private static Logger logger = Logger.getLogger(Main.class);
	
	
	public static void main(String[] args){
		logger.info("Start program");
		logger.info("Create port");
		Port port = new Port(1000000, 5);
		Sea sea = new Sea(port, 100);
		sea.start();
		port.startDocksWorking();
	}
}
