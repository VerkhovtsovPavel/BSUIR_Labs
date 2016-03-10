package com.bsuir.wtlab3.exception;

public class LogicException extends Exception {
	private static final long serialVersionUID = 8821737106979124398L;

	public LogicException(DaoException e) {
		super(e);
	}

	public LogicException() {
		super();
	}
}
