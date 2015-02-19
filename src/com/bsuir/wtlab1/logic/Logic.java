package com.bsuir.wtlab1.logic;

import java.util.ArrayList;

import com.bsuir.wtlab1.source.TreasureStore;
import com.bsuir.wtlab1.source.dao.FileDao;
import com.bsuir.wtlab1.source.entity.Treasure;

/**
 * Class contains helpful methods for collections.
 *
 * @author Pavel_Verkhovtsov
 *
 */
public final class Logic {

	private FileDao dataSource;

	/**
	 * Initialize dataSource and treasure store.
	 * 
	 */
	public Logic() {
		this.dataSource = new FileDao();
		initializeTreasuresStore();
	}

	/**
	 * Find in list treasure with maximum cost.
	 *
	 * @return treasure with maximum cost
	 */
	public Treasure findDearestTreaser() {
		Treasure dearestTreaser = new Treasure("", 0);

		for (Treasure treasure : TreasureStore.getInstance().getAllTreasures()) {
			if (treasure.getCost() > dearestTreaser.getCost()) {
				dearestTreaser = treasure;
			}
		}
		return dearestTreaser;
	}

	/**
	 * Create list of treasures on the required amount.
	 *
	 * @param sum
	 *            required amount
	 * @return treasures set           
	 */
	public ArrayList<Treasure> findTreasuresOnSum(final long sum) {
		ArrayList<Treasure> treaserSet = new ArrayList<Treasure>();
		long setCost = 0;

		for (Treasure treasure : TreasureStore.getInstance().getAllTreasures()) {
			if (treasure.getCost() + setCost <= sum) {
				treaserSet.add(treasure);
				setCost = setCost + treasure.getCost();
			}
		}
		return treaserSet;
	}

	/**
	 * Get all treasures.
	 *
	 * @return treasures
	 */
	public ArrayList<Treasure> getAllTreasures() {
		return TreasureStore.getInstance().getAllTreasures();
	}

	public void initializeTreasuresStore() {
		TreasureStore.getInstance().addTreasures(dataSource.getDate());
	}
}
