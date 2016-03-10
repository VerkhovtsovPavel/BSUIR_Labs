package com.bsuir.wtlab5.dao.factory;

import com.bsuir.wtlab5.dao.AdminMenuDao;
import com.bsuir.wtlab5.dao.UserMenuDao;
import com.bsuir.wtlab5.dao.db.UserMySQLDao;

public class MySQLMenuDaoFactory extends MenuDaoFactory {

	private static MySQLMenuDaoFactory instance;
	
	private MySQLMenuDaoFactory(){};

	public UserMenuDao getUserDao() {
		return UserMySQLDao.getInstance();
	}
	
	public AdminMenuDao getAdminDao() {
		return null;
	}

	public static MenuDaoFactory getFactory() {
		if (instance == null) {
			instance = new MySQLMenuDaoFactory();
		}
		return instance;
	}
}
