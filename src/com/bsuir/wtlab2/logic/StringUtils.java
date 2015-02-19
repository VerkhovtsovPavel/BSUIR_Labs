package com.bsuir.wtlab2.logic;

import java.util.ArrayList;

public class StringUtils {
	public static String buildString(final ArrayList<?> list) {
		StringBuilder builder = new StringBuilder();
		for (Object object : list) {
			builder.append(object.toString() + "\n");
		}
		return builder.toString();
	}

}
