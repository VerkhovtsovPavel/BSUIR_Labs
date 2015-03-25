package com.bsuir.wtlab3.command.commands;

import com.bsuir.wtlab3.command.Command;
import com.bsuir.wtlab3.dao.DaoFactory;
import com.bsuir.wtlab3.source.Notepad;

public class SaveCommand implements Command {

	@Override
	public Object execute(String request) {
		DaoFactory.getFactory().getUserDao().saveNotes(Notepad.getInstance().getNotes());
		return null; //TODO ?????????
	}

}
