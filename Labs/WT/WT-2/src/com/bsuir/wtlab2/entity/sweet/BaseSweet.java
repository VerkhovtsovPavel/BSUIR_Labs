package com.bsuir.wtlab2.entity.sweet;

import com.bsuir.wtlab2.entity.GiftElement;

public abstract class BaseSweet extends GiftElement {

	private int sweetness;

	public BaseSweet(int sweethess, int cost) {
		super(cost);
		this.sweetness = sweethess;
	}

	@Override
	public boolean equals(Object obj) {
		if(this==obj){
			return true;
		}
		
		if (this.getClass()!=obj.getClass() || obj==null) {
			return false;
		}

		BaseSweet sweet = (BaseSweet) obj;
		if (this.sweetness == sweet.sweetness && super.equals(obj)) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		return prime * sweetness + super.hashCode();
	}
	
	public int getSweetness(){
		return this.sweetness;
	}
}
