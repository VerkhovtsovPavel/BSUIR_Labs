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

	private static final String SELECT_MENU = "SELECT `idDish`, `dishName`, `cost`, GROUP_CONCAT(`Name`) AS `products` FROM `dish` JOIN `dish_has_products` ON `Dish_idDish`=`idDish` JOIN `products` ON `Products_idProducts` = `idProducts` GROUP BY (`idDish`);";

	private static UserMySQLDao instance;

	public static UserMySQLDao getInstance() {
		if (instance == null) {
			instance = new UserMySQLDao();
		}
		return instance;
	}

	private UserMySQLDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			log.error("DB driver not found", e);
			return;
		}
	}

	@Override
	public ArrayList<Dish> getMenu() throws DaoException {
		ArrayList<Dish> menu = new ArrayList<Dish>();
		Connection conn = null;
		try {
			try {
				conn = DriverManager.getConnection(DB_PATH, DB_USER_NAME,
						DB_PASSWORD);
				log.info("Connection open");
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery(SELECT_MENU);
				while (rs.next()) {
					int id = rs.getInt("idDish");
					String dishName = rs.getString("dishName");
					int cost = rs.getInt("cost");
					ArrayList<String> products = parseProducts(rs
							.getString("products"));

					menu.add(new Dish(id, dishName, cost, products));
				}
			} finally {
				if(conn!=null){
					conn.close();
					log.info("Connection close");
				}
				
			}
		} catch (SQLException e) {
			log.error("Error while get menu from DB");
			throw new DaoException("Error while get menu from DB", e);
		}
		return menu;

	}

	private ArrayList<String> parseProducts(String products) {
		ArrayList<String> productList = new ArrayList<String>();
		for(String product : products.split(",")){
			productList.add(product);
		}	
		return productList;
	}
}
