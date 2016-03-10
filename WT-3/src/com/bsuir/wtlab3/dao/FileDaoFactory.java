package com.bsuir.wtlab3.dao;

public class FileDaoFactory extends DaoFactory {

	private static FileDaoFactory instance;
	
	private FileDaoFactory(){};

	public UserDao getUserDao() {
		return UserFileDao.getInstance();
	}

	public static DaoFactory getFactory() {
		if (instance == null) {
			instance = new FileDaoFactory();
		}
		return instance;
	}
}
