package com.bsuir.wtlab5.model;

import com.bsuir.wtlab5.dao.factory.MenuDaoFactory;
import com.bsuir.wtlab5.entity.Dish;
import com.bsuir.wtlab5.exception.DaoException;
import com.bsuir.wtlab5.exception.LogicException;
import com.bsuir.wtlab5.source.MenuStorage;

public class MenuLogic {

	public void getMenu(String source) throws LogicException {
		try {
			MenuStorage.getInstanse().setMenu(
					MenuDaoFactory.getFactory(source).getUserDao().getMenu());
		} catch (DaoException e) {
			throw new LogicException("Error while get menu from " + source, e);
		}
	}

	public void saveMenu(String source) throws LogicException {
		try {
			MenuDaoFactory.getFactory(source).getAdminDao()
					.saveMenu(MenuStorage.getInstanse().getMenu());
		} catch (DaoException e) {
			throw new LogicException("Error while save menu from " + source, e);
		}
	}

	public String showMenu() {
		StringBuilder menuBuilder = new StringBuilder();

		for (Dish dish : MenuStorage.getInstanse().getMenu()) {
			menuBuilder.append(dish.getForUserString());
		}

		return menuBuilder.toString();
	}
}
