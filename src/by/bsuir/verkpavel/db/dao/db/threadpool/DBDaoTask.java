package by.bsuir.verkpavel.db.dao.db.threadpool;

import java.sql.ResultSet;

public class DBDaoTask {
	
	private String request;
	private ResultSet resultSet;
	private boolean useTrasaction;
	
	public DBDaoTask(String request, boolean isTransaction){
	    this.request = request;
	    this.useTrasaction = isTransaction;
	}
	
	public String getRequest() {
		return request;
	}
	
	public boolean getIsTransaction(){
	    return useTrasaction;
	}
	public ResultSet getResultSet() {
		return resultSet;
	}
	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}
	
}
