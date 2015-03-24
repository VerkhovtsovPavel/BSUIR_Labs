package com.bsuir.wtlab3.dao;

public abstract class DaoFactory {

	public abstract UserDao getUserDao();
	
	 private static String type = "file";

	public static DaoFactory getFactory() {
		switch (type) {
		case "file":
			return FileDaoFactory.getFactory();
		default:
			return null;
		}
	}
}
