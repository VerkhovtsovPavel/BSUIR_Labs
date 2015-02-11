package utils;

import java.util.ArrayList;

import data.Treasure;

/**
 * Class contains helpful methods for collections.
 *
 * @author Pavel_Verkhovtsov
 *
 */
public abstract class CollectionUtils {

	/**
	 * Find in list treasure with maximum cost.
	 *
	 * @param allTreasures
	 *            List of dragon treasure
	 * @return treasure with maximum cost
	 */
	public static Treasure findDearestTreaser(final ArrayList<Treasure> allTreasures) {
		Treasure dearestTreaser = new Treasure("", 0);

		for (Treasure treasure : allTreasures) {
			if (treasure.getCost() > dearestTreaser.getCost()) {
				dearestTreaser = treasure;
			}
		}
		return dearestTreaser;
	}

	/**
	 * Create list of treasures on the required amount
	 *
	 * @param allTreasures
	 *            list of dragon treasure
	 * @param sum
	 *            required amount
	 * @return list of treasures on the required amount
	 */
	public static ArrayList<Treasure> findTreaserOnSum(final ArrayList<Treasure> allTreasures, final long sum) {
		ArrayList<Treasure> treaserSet = new ArrayList<Treasure>();
		long setCost = 0;

		for (Treasure treasure : allTreasures) {
			if (treasure.getCost() + setCost <= sum) {
				treaserSet.add(treasure);
				setCost = setCost + treasure.getCost();
			}
		}
		return treaserSet;
	}
}
