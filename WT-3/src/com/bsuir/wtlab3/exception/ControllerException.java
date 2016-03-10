package com.bsuir.wtlab3.exception;

public class ControllerException extends Exception {

	public ControllerException(LogicException ex) {
		super(ex);
	}

	public ControllerException(String message) {
		super(message);
	}

	public ControllerException(String string, LogicException lex) {
		super(string, lex);
	}

	private static final long serialVersionUID = 4437609709577603912L;

}
