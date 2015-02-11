package data;

import gift.GiftElement;

import java.util.ArrayList;

public class GiftElementStore {

	private ArrayList<GiftElement> giftElementsStore = new ArrayList<GiftElement>();

	public void addElementOnGift(GiftElement giftElement) {
		giftElementsStore.add(giftElement);
	}

	public boolean removeElementFromGift(GiftElement giftElement) {
		return giftElementsStore.remove(giftElement);
	}

	public void clearGift() {
		giftElementsStore.clear();
	}

	public ArrayList<GiftElement> getGift() {
		return giftElementsStore;
	}

}
