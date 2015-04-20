package com.bsuir.wtlab5.dao.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.bsuir.wtlab5.dao.UserMenuDao;
import com.bsuir.wtlab5.entity.Dish;
import com.bsuir.wtlab5.exception.DaoException;


public class UserMySQLDao implements UserMenuDao {
	private static Logger log = Logger.getLogger(UserMySQLDao.class);

	private static final String DB_PATH = "jdbc:mysql://localhost:3306/menu";
	private static final String DB_USER_NAME = "root";
	private static final String DB_PASSWORD = "123456";
	
	private static final String SELECT_MENU = "";

	private static UserMySQLDao instance;

	public static UserMySQLDao getInstance() {
		if (instance == null) {
			instance = new UserMySQLDao();
		}
		return instance;
	}


	private UserMySQLDao(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			return;
		}
	}

	@Override
	public ArrayList<Dish> getMenu() throws DaoException {
		Connection conn = null;
		try {
			try {
				conn = DriverManager.getConnection(DB_PATH, DB_USER_NAME,
						DB_PASSWORD);
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery(SELECT_MENU);
				while (rs.next()) {
					String dishName = rs.getString("dishNmae");
					int cost = rs.getInt("cost");
					
					Dish currentDish = new Dish();
					currentDish.setCost(cost);
				}
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			throw new DaoException("Error", e);
		}
		return null;

	}
}
