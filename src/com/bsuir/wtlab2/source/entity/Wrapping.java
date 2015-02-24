package com.bsuir.wtlab2.source.entity;

public class Wrapping extends GiftElement {

	public String color;
	public String pattern;

	public Wrapping(String pattern, String color) {
		super(10);
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
		return "Wrapping with pattern " + this.pattern + " and " + this.color + " color";
	}

	@Override
	public int hashCode() {
		return super.hashCode() + this.color.hashCode() + this.pattern.hashCode();
	}
}
