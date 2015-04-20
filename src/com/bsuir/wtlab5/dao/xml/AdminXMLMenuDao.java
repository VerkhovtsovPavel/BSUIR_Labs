package com.bsuir.wtlab5.dao.xml;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.bsuir.wtlab5.dao.AdminMenuDao;
import com.bsuir.wtlab5.dao.UserMenuDao;
import com.bsuir.wtlab5.dao.db.UserMySQLDao;
import com.bsuir.wtlab5.entity.Dish;

public class AdminXMLMenuDao implements AdminMenuDao{
	private static Logger log = Logger.getLogger(UserMySQLDao.class);
	
	private static final String XML_FILE_PATH = "menu.xml";
	
	private static AdminXMLMenuDao instance;
	
	public static AdminXMLMenuDao getInstance(){
		if(instance == null){
			instance = new AdminXMLMenuDao();
		}
		return instance;
	}
	
	private AdminXMLMenuDao(){}

	@Override
	public ArrayList<Dish> getMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveMenu(ArrayList<Dish> menu) {
		// TODO Auto-generated method stub
		
	};

}
