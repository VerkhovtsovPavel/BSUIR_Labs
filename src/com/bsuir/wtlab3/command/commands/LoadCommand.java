package com.bsuir.wtlab3.command.commands;

import com.bsuir.wtlab3.command.Command;
import com.bsuir.wtlab3.dao.DaoFactory;
import com.bsuir.wtlab3.model.CommandLineParametersParser;
import com.bsuir.wtlab3.source.Notepad;

public class LoadCommand implements Command {

	@Override
	public Object execute(String request) {
		for(String unparseNote : DaoFactory.getFactory().getUserDao().getAllNotes()){
			Notepad.getInstance().addNote(CommandLineParametersParser.convertStringToNote(unparseNote));
		}
		return null; //TODO ????????????
	}

}
