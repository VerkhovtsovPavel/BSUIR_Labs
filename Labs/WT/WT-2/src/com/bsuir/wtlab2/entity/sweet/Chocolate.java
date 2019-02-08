package com.bsuir.wtlab2.entity.sweet;

public class Chocolate extends BaseSweet{

	private int percentageCocoa;
	
	public Chocolate(int sweethess, int percentageCocoa, int cost) {
		super(sweethess, cost);
		this.percentageCocoa = percentageCocoa;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this==obj){
			return true;
		}
		
		if (this.getClass()!=obj.getClass() || obj==null) {
			return false;
		}

		Chocolate sweet = (Chocolate) obj;
		if (this.percentageCocoa == sweet.percentageCocoa && super.equals(obj)) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}
