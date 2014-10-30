package utils;

import java.util.ArrayList;

public class Utils {

	public static ArrayList<String> makeList(String[] strings) {
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < strings.length; i++) {
			result.add(strings[i]);
		}
		return result;
	}
}
