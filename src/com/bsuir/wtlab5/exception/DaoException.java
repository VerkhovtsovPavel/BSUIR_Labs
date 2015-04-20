package com.bsuir.wtlab5.exception;

import java.io.IOException;

public class DaoException extends Exception{
	public DaoException(String string) {
		super(string);
	}

	public DaoException(String string, Exception e) {
		super(string, e);
	}

	private static final long serialVersionUID = 1L;

}
