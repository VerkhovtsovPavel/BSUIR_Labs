package com.bsuir.wtlab2.entity.sweet;

public class Zephyr extends BaseSweet{
	
	public String fruitSource;

	public Zephyr(int sweethess, String fruit, int cost) {
		super(sweethess, cost);
		this.fruitSource = fruit;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this==obj){
			return true;
		}
		
		if (this.getClass()!=obj.getClass() || obj==null) {
			return false;
		}

		Zephyr sweet = (Zephyr) obj;
		if (this.fruitSource == sweet.fruitSource && super.equals(obj)) {
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
