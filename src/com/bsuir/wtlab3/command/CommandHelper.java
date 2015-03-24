package com.bsuir.wtlab3.command;

import java.util.HashMap;
import java.util.Map;

public class CommandHelper {
	private Map<CommandName, Command> commands = new HashMap<>(); 
	
	public Command getCommand(CommandName commandName){
		return commands.get(commandName);
	}
}
