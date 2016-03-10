package com.bsuir.wtlab3.model.command.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.apache.log4j.Logger;

import com.bsuir.wtlab3.entity.Note;
import com.bsuir.wtlab3.exception.LogicException;
import com.bsuir.wtlab3.model.command.Command;
import com.bsuir.wtlab3.model.comparator.NoteDateComparator;
import com.bsuir.wtlab3.model.comparator.NoteEmailComparator;
import com.bsuir.wtlab3.model.comparator.NoteTextComparator;
import com.bsuir.wtlab3.model.comparator.NoteTopicComparator;
import com.bsuir.wtlab3.source.Notepad;

public class SortCommand implements Command {
	private final Logger log = Logger.getLogger(SortCommand.class);

	@Override
	public Object execute(String request) throws LogicException {
		log.debug("Execute sort command");
		sortBy(parseSortMethod(request), Notepad.getInstance().getNotes());
		return parseSortMethod(request);
	}

	private String parseSortMethod(String request) throws LogicException {
		request = request.replace("-", "");
		switch (request) {
		case "t":
			return "topic";
		case "e":
			return "e-mail";
		case "m":
			return "text";
		case "d":
			return "date";
		default :
			throw new LogicException();
		}	
	}
	
	

	private void sortBy(String method, ArrayList<Note> notepad) {
		Comparator<Note> comparator = null;
		log.debug("Sort by "+method);
		switch (method) {
		case "date":
			comparator = new NoteDateComparator();
			break;
		case "e-mail":
			comparator = new NoteEmailComparator();
			break;
		case "topic":
			comparator = new NoteTopicComparator();
			break;
		case "text":
			comparator = new NoteTextComparator();
			break;
		}
		Collections.sort(notepad, comparator);
	}
}
