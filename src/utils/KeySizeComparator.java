package utils;

import java.util.Comparator;
import java.util.Map.Entry;


public class KeySizeComparator implements Comparator<Entry<String, Integer>> {

	@Override
	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
		return 	o1.getKey().length()-o2.getKey().length();
	}
}
