package com.bsuir.wtlab2.entity.sweet;

public class CandiedRoastedNuts extends BaseSweet{

	private String nuts;
	
	public CandiedRoastedNuts(int sweethess, int cost, String nuts) {
		super(sweethess, cost);
		this.nuts = nuts;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this==obj){
			return true;
		}
		
		if (this.getClass()!=obj.getClass() || obj==null) {
			return false;
		}

		CandiedRoastedNuts sweet = (CandiedRoastedNuts) obj;
		if (this.nuts == sweet.nuts && super.equals(obj)) {
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
