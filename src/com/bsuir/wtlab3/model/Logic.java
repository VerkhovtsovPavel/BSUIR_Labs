package com.bsuir.wtlab3.model;

import java.util.ArrayList;

import com.bsuir.wtlab3.dao.DaoFactory;
import com.bsuir.wtlab3.dao.UserDao;
import com.bsuir.wtlab3.entity.Note;
import com.bsuir.wtlab3.source.Notepad;

public class Logic {
	private NoteFinder finder;
	private NoteSorter sorter;
	private CommandLineParametersParser parser;
	private UserDao dao;
	
	private static final String NOTE_SUCCESSFULLY_ADDED = "Note successfully added";
	private static final String NOTE_NOT_ADDED = "Note not added";
	
	private static final String NOTE_SORTED_BY_RESPONSE_FORMAT = "Notes sorted by %s";

	public Logic() {
		this.finder = new NoteFinder();
		this.sorter = new NoteSorter();
		this.dao = DaoFactory.getFactory().getUserDao();
		this.parser = new CommandLineParametersParser();
	}

	public void getNotesFromFile() {
		for(String unparseNote : dao.getAllNotes()){
			Notepad.getInstance().addNote(parser.convertStringToNote(unparseNote));
		}
	}

	public void saveNotesToFile() {
		dao.saveNotes(Notepad.getInstance().getNotes());
	}
	
	public String sortNotes(String request){
		sorter.sortBy(parser.parseSortMethod(request), Notepad.getInstance().getNotes());
		return String.format(NOTE_SORTED_BY_RESPONSE_FORMAT, parser.parseSortMethod(request));
	}
	
	public ArrayList<Note> findNotes(String request){
		return finder.tupleSearch(parser.parseSearchParameters(request), Notepad.getInstance().getNotes());
	}

	public String addNote(String request) {
		if(Notepad.getInstance().addNote(parser.convertStringToNote(request))){
			return NOTE_SUCCESSFULLY_ADDED;
		}
		return NOTE_NOT_ADDED;
	}
	
	public ArrayList<Note> getAllNotes(){
		return Notepad.getInstance().getNotes();
	}
	
	
	
}
