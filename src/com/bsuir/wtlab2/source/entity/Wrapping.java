package com.bsuir.wtlab2.source.entity;

public class Wrapping extends GiftElement {

	public String color;
	public String pattern;

	public Wrapping(String pattern, String color) {
		this.color = color;
		this.pattern = pattern;
		this.cost = 10;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Wrapping)) {
			return false;
		}

		Wrapping wrapping = (Wrapping) obj;
		if ((this.color.equals(wrapping.color)) && (this.pattern.equals(wrapping.pattern))) {
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
		final int prime = 31;
		int result = 1;
		result = prime * result + this.color.hashCode();
		result = prime * result + this.color.hashCode();
		return result;
	}

}
