package com.bsuir.wtlab3.model.command;

import com.bsuir.wtlab3.exception.LogicException;

public class Manager {
	private CommandHelper commandHelper = new CommandHelper();
	
	public Object execute(CommandName commandName, String request) throws LogicException{
		return commandHelper.getCommand(commandName).execute(request);
	}
}
