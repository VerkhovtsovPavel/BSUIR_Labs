package com.bsuir.wtlab3.command;

public class Manager {
	private CommandHelper commandHelper = new CommandHelper();
	
	public Object execute(CommandName commandName, String request){
		return commandHelper.getCommand(commandName).execute(request);
	}
}
