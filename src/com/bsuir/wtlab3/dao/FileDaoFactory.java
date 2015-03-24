package com.bsuir.wtlab3.dao;

public class FileDaoFactory extends DaoFactory{
        public UserDao getUserDao(){
        	return UserFileDao.getInstance();
        }
}
