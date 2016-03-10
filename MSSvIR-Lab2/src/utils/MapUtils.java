package utils;

import java.util.Map;
import java.util.Map.Entry;

public class MapUtils {

	public static void addToMap(String key, Map<String, Integer> map) {
		Integer value = 1;
		if (map.containsKey(key)) {
			value = map.get(key);
			value++;
		}
		map.put(key, value);
	}

	public static int findSumAllValuesInMap(Map<String, Integer> map) {
		int result = 0;
		for (Entry<String, Integer> entry : map.entrySet()) {
			result += entry.getValue();
		}
		return result;
	}
}
