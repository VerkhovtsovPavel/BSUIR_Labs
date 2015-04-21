package com.bsuir.wtlab5.exception;

public class LogicException extends Exception{

	private static final long serialVersionUID = 4369027580315449758L;

	public LogicException(String message, Exception e){
		super(message, e);
	}

}
