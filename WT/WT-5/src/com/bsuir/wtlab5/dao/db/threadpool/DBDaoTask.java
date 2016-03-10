package com.bsuir.wtlab5.dao.db.threadpool;

import java.sql.ResultSet;

public class DBDaoTask {
	
	private String request;
	private ResultSet resultSet;
	
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public ResultSet getResultSet() {
		return resultSet;
	}
	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}
	
}
