package com.bsuir.wtlab3.model.command;

import com.bsuir.wtlab3.exception.LogicException;

public interface Command {
	Object execute(String request) throws LogicException;
}
