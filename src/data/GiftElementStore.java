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

	public int getTotalCost() {
		int totalCost = 0;
		
		for (GiftElement giftElement : giftElementsStore){
			totalCost = totalCost + giftElement.cost;
		}
		return totalCost;
	}
}
