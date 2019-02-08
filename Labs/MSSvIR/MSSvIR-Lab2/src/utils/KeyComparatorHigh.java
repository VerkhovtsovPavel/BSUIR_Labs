package utils;

import java.util.Comparator;


public class KeyComparatorHigh implements Comparator<String> {

	@Override
	public int compare(String firstEntry, String secondEntry) {
		return 	secondEntry.compareTo(firstEntry);
	}
}
