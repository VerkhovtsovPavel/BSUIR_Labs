package utils;

import java.util.ArrayList;

public class StringUtils {
	// TODO Refactor use StringBuffer
	public static String buildString(ArrayList<?> list) {
		String result = "";
		for (Object object : list) {
			result += object.toString() + "\n";
		}
		return result;
	}

}
