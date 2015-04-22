package com.bsuir.wtlab5.dao.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.bsuir.wtlab5.dao.UserMenuDao;
import com.bsuir.wtlab5.dao.db.threadpool.DBDaoTask;
import com.bsuir.wtlab5.dao.db.threadpool.DBDaoThreadPool;
import com.bsuir.wtlab5.entity.Dish;
import com.bsuir.wtlab5.exception.DaoException;

public class UserMySQLDao implements UserMenuDao {
	private static Logger log = Logger.getLogger(UserMySQLDao.class);

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
		try {
			DBDaoTask task = new DBDaoTask();
			task.setRequest(SELECT_MENU);

			DBDaoThreadPool.getInstance().addTask(task);
			synchronized (task) {
				task.wait();
			}
			
			ResultSet rs = task.getResultSet();
			if(rs == null){
				throw new DaoException("Incorrect request");
			}

			while (rs.next()) {
				int id = rs.getInt("idDish");
				String dishName = rs.getString("dishName");
				int cost = rs.getInt("cost");
				ArrayList<String> products = parseProducts(rs.getString("products"));

				menu.add(new Dish(id, dishName, cost, products));
			}
		} catch (SQLException | InterruptedException e) {
			log.error("Error while get menu from DB");
			throw new DaoException("Error while get menu from DB", e);
		}
		return menu;

	}

	private ArrayList<String> parseProducts(String products) {
		ArrayList<String> productList = new ArrayList<String>();
		for (String product : products.split(",")) {
			productList.add(product);
		}
		return productList;
	}
}
