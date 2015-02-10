package utils;

import java.util.ArrayList;

import data.Treasure;

public class CollectionUtils {

	public static Treasure findDearestTreaser(ArrayList<Treasure> allTreasures) {
		Treasure dearestTreaser = new Treasure("", 0);
		
		for (Treasure treasure : allTreasures){
			if(treasure.cost>dearestTreaser.cost){
				dearestTreaser = treasure;
			}
		}
		return dearestTreaser;
	}

	public static ArrayList<Treasure> findTreaserOnSum(ArrayList<Treasure> allTreasures, long sum) {
		ArrayList<Treasure> treaserSet = new ArrayList<Treasure>();
		long setCost = 0;
		
		for (Treasure treasure : allTreasures){
			if(treasure.cost+setCost<=sum){
				treaserSet.add(treasure);
				setCost= setCost+treasure.cost;
			}
		}
		return treaserSet;
	}
	

}
