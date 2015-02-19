package com.bsuir.wtlab1.source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.bsuir.wtlab1.source.entity.Treasure;

/**
 *
 * @author Pavel_Verkhovtsov
 */
public class TreasureStore {
	private static TreasureStore instance;

	private ArrayList<Treasure> treasures = new ArrayList<Treasure>();

	/**
	 * Return or create and return treasure store instance
	 * 
	 * @return treasure instance
	 */
	public static TreasureStore getInstance() {
		if (instance == null) {
			instance = new TreasureStore();
		}
		return instance;
	}

	/**
	 * Add treasure in list.
	 * 
	 * @param treasuresMap
	 *            map of treasures
	 */
	public void addTreasures(HashMap<String, Long> treasuresMap) {
		for (Entry<String, Long> treasure : treasuresMap.entrySet()) {
			treasures.add(new Treasure(treasure.getKey(), treasure.getValue()));
		}
	}

	/**
	 * Getter from treasures field.
	 * 
	 * @return treasures
	 */
	public ArrayList<Treasure> getAllTreasures() {
		return treasures;
	}
}
