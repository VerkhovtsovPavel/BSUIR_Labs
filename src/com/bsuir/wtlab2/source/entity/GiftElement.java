package com.bsuir.wtlab2.source.entity;

public abstract class GiftElement{
	
	protected int cost;
	
	public abstract boolean equals(Object obj);
	public abstract String toString();
	public abstract int hashCode();
	public abstract int getCost();
}
