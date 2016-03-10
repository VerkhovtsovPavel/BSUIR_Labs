package com.bsuir.wtlab3.model.command.commands;

import org.apache.log4j.Logger;

import com.bsuir.wtlab3.dao.DaoFactory;
import com.bsuir.wtlab3.exception.DaoException;
import com.bsuir.wtlab3.exception.LogicException;
import com.bsuir.wtlab3.model.command.Command;
import com.bsuir.wtlab3.source.Notepad;

public class SaveCommand implements Command {
	private final Logger log = Logger.getLogger(SaveCommand.class);

	@Override
	public Object execute(String request) throws LogicException {
		
		log.debug("Execute save command");
		try {
			DaoFactory.getFactory().getUserDao().saveNotes(Notepad.getInstance().getNotes());
		} catch (DaoException e) {
			throw new LogicException(e);
		}
		return true;
	}

}
