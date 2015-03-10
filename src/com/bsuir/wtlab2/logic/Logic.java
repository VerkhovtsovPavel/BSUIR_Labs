package com.bsuir.wtlab2.logic;

import java.util.ArrayList;

import com.bsuir.wtlab2.source.GiftElementStore;
import com.bsuir.wtlab2.source.entity.GiftElement;
import com.bsuir.wtlab2.source.factory.GiftFactory;

public class Logic {

	private GiftFactory giftFactory;

	public Logic() {
		this.giftFactory = new GiftFactory();
	}

	public ArrayList<GiftElement> getGift() {
		return GiftElementStore.getInstance().getGift();
	}

	public Object getTotalCost() {
		return GiftElementStore.getInstance().getTotalCost();
	}

	public void clearGift() {
		GiftElementStore.getInstance().clearGift();
	}

	public boolean removeElementFromGift(String request) {
		return GiftElementStore.getInstance().removeElementFromGift(createGiftElement(request));
	}

	public boolean addElementOnGift(String request) {
		return GiftElementStore.getInstance().addElementOnGift(createGiftElement(request));
	}

	private GiftElement createGiftElement(String request) {
		String objectType = FactoryParametersParser.getObjectType(request);
		return giftFactory.getGiftElement(objectType, FactoryParametersParser.getParams(request));
	}

}
