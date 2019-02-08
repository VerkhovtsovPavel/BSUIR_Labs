package com.bsuir.wtlab3.model.command.commands;

import org.apache.log4j.Logger;

import com.bsuir.wtlab3.model.command.Command;
import com.bsuir.wtlab3.source.Notepad;

public class GetCommand implements Command {
	private final Logger log = Logger.getLogger(GetCommand.class);

	@Override
	public Object execute(String request) {
		log.debug("Execute get command");
		return Notepad.getInstance().getNotes();
	}

}
