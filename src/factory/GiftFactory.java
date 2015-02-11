package factory;

import gift.GiftElement;
import gift.Sweet;
import gift.Wrapping;

public class GiftFactory {

	public GiftElement getGiftElement(boolean isSweet) {
		if (isSweet) {
			return new Sweet();
		} else {
			return new Wrapping();
		}
	}
}
