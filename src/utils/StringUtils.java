package utils;

import java.util.ArrayList;

/**
 * Class contains helpful methods for string.
 * @author Pavel_Verkhovtsov
 *
 */
public abstract class StringUtils {
	// TODO Refactor use StringBuffer
	/**
	 * Convert list to string
	 * @param list list
	 * @return string
	 */
	public static String buildString(final ArrayList<?> list) {
		StringBuilder builder = new StringBuilder();
		for (Object object : list) {
			builder.append(object.toString() + "\n");
		}
		return builder.toString();
	}

}
