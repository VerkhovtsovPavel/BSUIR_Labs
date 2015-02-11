package data;

/**
 * Class describe treasure
 * @author Pavel_Verkhovtsov
 *
 */
public class Treasure {
	private String name;
	private long cost;

	/**
	 * Constructor.
	 * @param treasureName treasure name
	 * @param treasureCost treasure cost
	 */
	public Treasure(final String treasureName, final long treasureCost){
		this.name = treasureName;
		this.cost = treasureCost;
	}

	@Override
	public String toString(){
		return this.name+"  "+this.cost;
	}

	/**
	 * Getter by cost field.
	 * @return treasure cost
	 */
	public long getCost(){
		return this.cost;
	}
}
