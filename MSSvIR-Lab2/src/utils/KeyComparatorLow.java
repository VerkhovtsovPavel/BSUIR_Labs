package utils;

import java.util.Comparator;


public class KeyComparatorLow implements Comparator<String> {

	@Override
	public int compare(String firstEntry, String secondEntry) {
		return 	firstEntry.compareTo(secondEntry);
	}
}
