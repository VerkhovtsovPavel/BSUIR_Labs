package utils;

import java.util.Comparator;
import java.util.Map.Entry;


public class KeySizeComparator implements Comparator<Entry<String, Integer>> {

	@Override
	public int compare(Entry<String, Integer> firstEntry, Entry<String, Integer> secondEntry) {
		return 	secondEntry.getKey().compareTo(firstEntry.getKey());
	}
}
