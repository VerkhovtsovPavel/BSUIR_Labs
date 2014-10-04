package utils;

import java.util.HashMap;

public class MapUtils {
	
	public static void addToMap(String key, HashMap<String, Integer> map){
		Integer value = 1;
		if (map.containsKey(key)){
			value= map.get(key);
			value++;
		}
		map.put(key, value);
	}
}
