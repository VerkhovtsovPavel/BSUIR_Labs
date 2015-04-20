package com.bsuir.wtlab5.dao;

import java.util.ArrayList;

import com.bsuir.wtlab5.entity.Dish;

public interface AdminMenuDao extends UserMenuDao{
	void saveMenu(ArrayList<Dish> menu);
}
