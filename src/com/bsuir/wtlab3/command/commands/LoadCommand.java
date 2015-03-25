package com.bsuir.wtlab3.command.commands;

import com.bsuir.wtlab3.command.Command;
import com.bsuir.wtlab3.dao.DaoFactory;

public class LoadCommand implements Command {

	@Override
	public Object execute(String request) {
		Command add = new AddCommand();
		for(String unparseNote : DaoFactory.getFactory().getUserDao().getAllNotes()){
			add.equals(unparseNote);
		}
		return null; //TODO ????????????
	}

}
