package factory;

import gift.GiftElement;
import gift.Sweet;
import gift.Wrapping;

public class GiftFactory {

	public GiftElement getGiftElement(final String objectType, String params) {
		switch (objectType) {
		case "Sweet":
			//TODO parse params
			return new Sweet();
		case "Wrapping":
			//TODO pars params
			return new Wrapping();
		default:
			return null;
		}
	}
}
