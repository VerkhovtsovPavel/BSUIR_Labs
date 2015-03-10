package com.bsuir.wtlab2.source;

import java.util.ArrayList;

import com.bsuir.wtlab2.source.entity.GiftElement;

public class GiftElementStore {

	private ArrayList<GiftElement> giftElementsStore = new ArrayList<GiftElement>();

	private static GiftElementStore instance;

	public static GiftElementStore getInstance() {
		if (instance == null) {
			instance = new GiftElementStore();
		}
		return instance;
	}

	private GiftElementStore() {
	};

	public boolean addElementOnGift(GiftElement giftElement) {
		return giftElementsStore.add(giftElement);
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

		for (GiftElement giftElement : giftElementsStore) {
			totalCost = totalCost + giftElement.getCost();
		}
		return totalCost;
	}
}
