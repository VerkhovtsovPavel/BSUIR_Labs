package controller;

import utils.CollectionUtils;
import utils.StringUtils;
import data.Cave;

public class Controller {

	private Cave cave;

	public Controller() {
		this.cave = new Cave();
	}

	public String process(String choose) {
		try {
			String[] parseChoose = choose.split(":[\t ]*");
			String request = parseChoose[0].trim();
			switch (request) {
			case "Print all":
				return StringUtils.buildString(cave.getAllTreasures());
			case "Print dearest treaser":
				return CollectionUtils.findDearestTreaser(cave.getAllTreasures()).toString();
			case "Get treasers on sum":
				long parameter = Long.valueOf(parseChoose[1].trim());
				return StringUtils.buildString(CollectionUtils.findTreaserOnSum(cave.getAllTreasures(), parameter));
			case "Exit":
				return "Goodbye";
			default:
				return "Incorrect command";
			}
		} catch (NumberFormatException e) {
			return "Incorrect command";
		}
	}

}
