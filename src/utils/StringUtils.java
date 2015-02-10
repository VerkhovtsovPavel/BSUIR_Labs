package utils;

import java.util.ArrayList;

import data.Treasure;

public class StringUtils {
	// TODO Refactor use StringBuffer
	public static String buildString(ArrayList<Treasure> treasures) {
		String result = "";
		for (Treasure treasure : treasures) {
			result += treasure.toString() + "\n";
		}

		return result;

	}

}
