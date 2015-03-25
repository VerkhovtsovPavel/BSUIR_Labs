package com.bsuir.wtlab3.command.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.bsuir.wtlab3.command.Command;
import com.bsuir.wtlab3.entity.Note;
import com.bsuir.wtlab3.source.Notepad;
import com.bsuir.wtlab3.source.comparator.NoteDateComparator;
import com.bsuir.wtlab3.source.comparator.NoteEmailComparator;
import com.bsuir.wtlab3.source.comparator.NoteTextComparator;
import com.bsuir.wtlab3.source.comparator.NoteTopicComparator;

public class SortCommand implements Command {

	@Override
	public Object execute(String request) {
		sortBy(parseSortMethod(request), Notepad.getInstance().getNotes());
		return parseSortMethod(request);
	}

	private String parseSortMethod(String request) {
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
			return request; //TODO Exception
		}	
	}
	
	

	private void sortBy(String method, ArrayList<Note> notepad) {
		Comparator<Note> comparator = null;
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
