package com.bsuir.wtlab2.entity.wrapping;

import com.bsuir.wtlab2.entity.GiftElement;

public class Wrapping extends GiftElement {

	public String color;
	public String pattern;

	public Wrapping(String pattern, String color, int cost) {
		super(cost);
		this.color = color;
		this.pattern = pattern;
	}

	@Override
	public boolean equals(Object obj) {
		if(this==obj){
			return true;
		}
		
		if (this.getClass()!=obj.getClass() || obj==null) {
			return false;
		}

		Wrapping wrapping = (Wrapping) obj;
		if (this.color.equals(wrapping.color) && this.pattern.equals(wrapping.pattern) && super.equals(obj)) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public String toString() {
		return "Wrapping with pattern " + this.pattern + " and " + this.color + " color and cost "+getCost();
	}

	@Override
	public int hashCode() {
		return super.hashCode() + this.color.hashCode() + this.pattern.hashCode();
	}
}
