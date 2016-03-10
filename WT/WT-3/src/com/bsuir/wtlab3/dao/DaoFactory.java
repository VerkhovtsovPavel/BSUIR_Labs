package com.bsuir.wtlab3.dao;

import com.bsuir.wtlab3.exception.DaoException;

public abstract class DaoFactory {

	public abstract UserDao getUserDao();
	
	private static String type = "file";

	public static DaoFactory getFactory() throws DaoException {
		switch (type) {
		case "file":
			return FileDaoFactory.getFactory();
		default:
			throw new DaoException("Incorrect dao type");
		}
	}
}
