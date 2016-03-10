package com.bsuir.wtlab2.entity;

public abstract class GiftElement{
	
	private int cost;
	
	public GiftElement(int cost) {
		this.cost = cost;
	}
	public boolean equals(Object obj){
		if(this==obj){
			return true;
		}
		
		if (this.getClass()!=obj.getClass() || obj==null) {
			return false;
		}

		GiftElement giftElement = (GiftElement) obj;
		if (this.cost == giftElement.cost) {
			return true;
		} else {
			return false;
		}
	}
	public int hashCode(){
		return 31 * this.cost;
	}
	
	public int getCost(){
		return this.cost;
	}
	
	public abstract String toString();
}
