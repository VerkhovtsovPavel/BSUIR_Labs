package com.bsuir.wtlab3.command.commands;

import com.bsuir.wtlab3.command.Command;
import com.bsuir.wtlab3.model.CommandLineParametersParser;
import com.bsuir.wtlab3.source.Notepad;

public class AddCommand implements Command{

	@Override
	public Object execute(String request) {
		return	Notepad.getInstance().addNote(CommandLineParametersParser.convertStringToNote(request));
	}

	

}
