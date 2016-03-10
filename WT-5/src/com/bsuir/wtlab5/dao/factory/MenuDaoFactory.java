package com.bsuir.wtlab5.dao.factory;

import com.bsuir.wtlab5.dao.AdminMenuDao;
import com.bsuir.wtlab5.dao.UserMenuDao;
import com.bsuir.wtlab5.exception.DaoException;

public abstract class MenuDaoFactory {

	public abstract UserMenuDao getUserDao();
	public abstract AdminMenuDao getAdminDao();
	
	private static String type = "file";

	public static MenuDaoFactory getFactory(String type) throws DaoException {
		switch (type) {
		case "db":
			return MySQLMenuDaoFactory.getFactory();
		case "xml":
			return XMLMenuDaoFactory.getFactory();
		default:
			throw new DaoException("Incorrect dao type");
		}
	}
	
	public static MenuDaoFactory getFactory() throws DaoException {
		return getFactory(type);
	}
}
