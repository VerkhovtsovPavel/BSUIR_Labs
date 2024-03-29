package com.bsuir.wtlab3.model.command.commands;

import org.apache.log4j.Logger;

import com.bsuir.wtlab3.dao.DaoFactory;
import com.bsuir.wtlab3.exception.DaoException;
import com.bsuir.wtlab3.exception.LogicException;
import com.bsuir.wtlab3.model.command.Command;

public class LoadCommand implements Command {
	private final Logger log = Logger.getLogger(LoadCommand.class);

	@Override
	public Object execute(String request) throws LogicException {
		log.debug("Execute load command");
		Command add = new AddCommand();
		try {
			for(String unparseNote : DaoFactory.getFactory().getUserDao().getAllNotes()){
				add.equals(unparseNote);
			}
		} catch (DaoException e) {
			throw new LogicException(e);
		}
		return true;
	}

}
