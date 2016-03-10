package com.bsuir.wtlab3.model.command;

import java.util.HashMap;
import java.util.Map;

import com.bsuir.wtlab3.model.command.commands.AddCommand;
import com.bsuir.wtlab3.model.command.commands.FindCommand;
import com.bsuir.wtlab3.model.command.commands.GetCommand;
import com.bsuir.wtlab3.model.command.commands.LoadCommand;
import com.bsuir.wtlab3.model.command.commands.SaveCommand;
import com.bsuir.wtlab3.model.command.commands.SortCommand;

public class CommandHelper {
	private Map<CommandName, Command> commands = new HashMap<>();

	public Command getCommand(CommandName commandName) {
		return commands.get(commandName);
	}

	public CommandHelper() {
		commands.put(CommandName.ADD, new AddCommand());
		commands.put(CommandName.FIND, new FindCommand());
		commands.put(CommandName.GET, new GetCommand());
		commands.put(CommandName.LOAD, new LoadCommand());
		commands.put(CommandName.SAVE, new SaveCommand());
		commands.put(CommandName.SORT, new SortCommand());
	}
}
