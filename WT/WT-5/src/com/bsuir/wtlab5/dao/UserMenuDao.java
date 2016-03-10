package com.bsuir.wtlab5.dao;

import java.util.ArrayList;

import com.bsuir.wtlab5.entity.Dish;
import com.bsuir.wtlab5.exception.DaoException;

public interface UserMenuDao {
		ArrayList<Dish> getMenu() throws DaoException;
}
