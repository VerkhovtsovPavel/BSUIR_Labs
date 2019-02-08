package com.bsuir.wtlab1.source.entity;

/**
 * Class describe treasure
 * 
 * @author Pavel_Verkhovtsov
 *
 */
public class Treasure {
	private String name;
	private long cost;

	private static final String TO_STRING_FORMAT = "Treasure \"%s\" with cost %s";

	/**
	 * Constructor.
	 * 
	 * @param treasureName
	 *            treasure name
	 * @param treasureCost
	 *            treasure cost
	 */
	public Treasure(final String treasureName, final long treasureCost) {
		this.name = treasureName;
		this.cost = treasureCost;
	}

	@Override
	public String toString() {
		return String.format(TO_STRING_FORMAT, this.name, this.cost);
	}

	/**
	 * Getter by cost field.
	 * 
	 * @return treasure cost
	 */
	public long getCost() {
		return this.cost;
	}

	/**
	 * Getter by name field.
	 * 
	 * @return treasure name
	 */
	public String getName() {
		return this.name;
	}
}
