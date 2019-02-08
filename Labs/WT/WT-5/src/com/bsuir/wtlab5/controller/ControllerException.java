package com.bsuir.wtlab5.controller;

import com.bsuir.wtlab5.exception.LogicException;

public class ControllerException extends Exception {

	private static final long serialVersionUID = -7346465842476176948L;

	public ControllerException(String message, LogicException e) {
		super(message, e);
	}

	public ControllerException(String message) {
		super(message);
	}

}
