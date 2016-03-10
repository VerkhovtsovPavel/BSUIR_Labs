package by.bsuir.verkpavel.db.dao.db.threadpool;

import java.sql.ResultSet;

public class DBTask {
	
	private String request;
	private ResultSet resultSet;
	private boolean useTrasaction;
	private int number;
	
	public DBTask(String request, boolean isTransaction, int number){
	    this.request = request;
	    this.useTrasaction = isTransaction;
	    this.number = number;
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

    public int getNumber() {
        return number;
    }	
}
