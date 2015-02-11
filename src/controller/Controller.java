package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utils.StringUtils;

import data.GiftElementStore;
import factory.GiftFactory;

public class Controller {

	private static final String COMMAND_VALIDATE_REGEXP = "(((Add)|(Delete)) *(((wrapper with) *\\w* *(patterns and) *\\w* *(color))|((sweet with sweetness) \\d*|(max sweetness sweet))))|(((Clear)|(Print)) *(present))";
	private GiftElementStore gift;
	private GiftFactory giftFactory;

	public Controller() {
		this.gift = new GiftElementStore();
		this.giftFactory = new GiftFactory();
	}

	public String process(String request) {
		if (!checkCommand(request)) {
			return "Incorrect command";
		}
		String commantType = request.split(":[\t ]*")[0];
		switch (commantType) {
		case "Add":
			return addCommands();
		case "Delete":
			return deleteCommands();
		case "Print":
			return StringUtils.buildString(gift.getGift());
		case "Clear":
			gift.clearGift();
			return "All gift elements deleted";
		case "Exit":
			return "Goodbye";
		default:
			return "Incorrect command";
		}
	}

	private String addCommands() {
		giftFactory.getGiftElement(true);
		return null;
	}

	private String deleteCommands() {
		return null;
	}

	private boolean checkCommand(String request) {
		Pattern functionPattern = Pattern.compile(COMMAND_VALIDATE_REGEXP);
		Matcher matcher = functionPattern.matcher(request);
		return matcher.find();
	}

}
