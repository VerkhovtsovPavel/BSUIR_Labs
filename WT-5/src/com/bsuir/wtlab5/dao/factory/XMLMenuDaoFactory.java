package com.bsuir.wtlab5.dao.factory;

import com.bsuir.wtlab5.dao.AdminMenuDao;
import com.bsuir.wtlab5.dao.UserMenuDao;
import com.bsuir.wtlab5.dao.xml.AdminXMLMenuDao;

public class XMLMenuDaoFactory extends MenuDaoFactory{

	private static XMLMenuDaoFactory instance;
	
	private XMLMenuDaoFactory(){};

	public UserMenuDao getUserDao() {
		return AdminXMLMenuDao.getInstance();
	}
	
	public AdminMenuDao getAdminDao() {
		return AdminXMLMenuDao.getInstance();
	}

	public static MenuDaoFactory getFactory() {
		if (instance == null) {
			instance = new XMLMenuDaoFactory();
		}
		return instance;
}
}
