package com.bsuir.wtlab5.exception;

public class DaoException extends Exception{

	private static final long serialVersionUID = -7930827196550582146L;

	public DaoException(String message, Exception e) {
		super(message, e);
	}

	public DaoException(String message) {
		super(message);
	}


}
