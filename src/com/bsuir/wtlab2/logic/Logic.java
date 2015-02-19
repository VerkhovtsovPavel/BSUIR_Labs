package com.bsuir.wtlab2.logic;

import java.util.ArrayList;

import com.bsuir.wtlab2.source.GiftElementStore;
import com.bsuir.wtlab2.source.entity.GiftElement;
import com.bsuir.wtlab2.source.factory.GiftFactory;

public class Logic {

	private GiftElementStore gift;
	private GiftFactory giftFactory;

	public Logic() {
		this.gift = new GiftElementStore();
		this.giftFactory = new GiftFactory();
	}

	public ArrayList<GiftElement> getGift() {
		return gift.getGift();
	}

	public Object getTotalCost() {
		return gift.getTotalCost();
	}

	public void clearGift() {
		gift.clearGift();
	}

	public boolean removeElementFromGift(String request) {
		return gift.removeElementFromGift(createGiftElement(request));
	}

	public void addElementOnGift(String request) {
		gift.addElementOnGift(createGiftElement(request));
	}

	private GiftElement createGiftElement(String request) {
		String objectType = FactoryParametersParser.getObjectType(request);
		String params = FactoryParametersParser.getParams(request);
		return giftFactory.getGiftElement(objectType, params);
	}

}
