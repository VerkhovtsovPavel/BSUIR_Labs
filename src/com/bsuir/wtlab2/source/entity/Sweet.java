package com.bsuir.wtlab2.source.entity;

public class Sweet extends GiftElement {

	public int sweetness;

	public Sweet(int sweethess) {
		super(sweethess * 2);
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

		Sweet sweet = (Sweet) obj;
		if (this.sweetness == sweet.sweetness && super.equals(obj)) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public String toString() {
		return "Sweet with sweetness " + this.sweetness;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		return prime * sweetness + super.hashCode();
	}
}
