package com.bsuir.wtlab3.exception;

public class DaoException extends Exception {
	private static final long serialVersionUID = 2878727851078977027L;

	public DaoException(String string) {
		super(string);
	}

	public DaoException(String string, Exception e) {
		super(string, e);
	}

}
