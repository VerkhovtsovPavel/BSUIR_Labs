package com.bsuir.wtlab2.source.entity.sweet;

public class CandiedRoastedNuts extends BaseSweet {

	private String nuts;

	public CandiedRoastedNuts(int sweethess, String nuts, int cost) {
		super(sweethess, cost);
		this.nuts = nuts;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (this.getClass() != obj.getClass() || obj == null) {
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
		return "Candied roasted nuts with " + this.nuts + " sweetness " + getSweetness() + " cost " + getCost();
	}
}
