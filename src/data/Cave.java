package data;

import java.util.ArrayList;
import java.util.Map.Entry;

import utils.FileUtils;


public class Cave {
	private ArrayList<Treasure> treasures = new ArrayList<Treasure>();
	public boolean dragonInCave = true;
	
	public Cave(){
		addTreasures();
	}
	
	private void addTreasures(){
		for(Entry<String, Long> treasure : FileUtils.getTreasureList().entrySet()){
			treasures.add(new Treasure(treasure.getKey(),treasure.getValue()));
		}
	}
	
	public ArrayList<Treasure> getAllTreasures() {
		return treasures;
	}
}
