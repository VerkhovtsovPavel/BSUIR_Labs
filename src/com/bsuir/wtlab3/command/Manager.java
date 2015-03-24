package com.bsuir.wtlab3.command;

public class Manager {
	private CommandHelper commandHelper = new CommandHelper();
	
	public void execute(String commandName, String request){
		commandHelper.getCommand(CommandName.valueOf(commandName)).execute(request);
	}
}
