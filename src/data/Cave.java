package data;

import java.util.ArrayList;
import java.util.Map.Entry;

import utils.FileUtils;

/**
 *
 * @author Pavel_Verkhovtsov
 */
public class Cave {
	private ArrayList<Treasure> treasures = new ArrayList<Treasure>();

	/**
	 * Initialize treasure list.
	 */
	public Cave() {
		addTreasures();
	}

	/**
	 * Add treasure in list
	 */
	private void addTreasures() {
		for (Entry<String, Long> treasure : FileUtils.getTreasureList().entrySet()) {
			treasures.add(new Treasure(treasure.getKey(), treasure.getValue()));
		}
	}

	/**
	 * Getter from treasures field
	 *
	 * @return treasures
	 */
	public ArrayList<Treasure> getAllTreasures() {
		return treasures;
	}
}
