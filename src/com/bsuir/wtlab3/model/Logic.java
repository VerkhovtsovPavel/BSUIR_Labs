package com.bsuir.wtlab3.model;

import java.util.ArrayList;

import com.bsuir.wtlab3.dao.FileDao;
import com.bsuir.wtlab3.source.Notepad;
import com.bsuir.wtlab3.source.entity.Note;

public class Logic {
	private NoteFinder finder;
	private NoteSorter sorter;
	private CommandLineParametersParser parser;
	private FileDao dao;

	public Logic() {
		this.finder = new NoteFinder();
		this.sorter = new NoteSorter();
		this.dao = new FileDao();
		this.parser = new CommandLineParametersParser();
	}

	public void getNotesFromFile() {
		for(String unparseNote : dao.getNotesFromFile()){
			Notepad.getInstance().addNote(parser.convertStringToNote(unparseNote));
		}
	}

	public void saveNotesToFile() {
		dao.saveNotesToFile(Notepad.getInstance().getNotes());
	}
	
	public void sortNotes(String request){
		sorter.sortBy(parser.parseSortMethod(request), Notepad.getInstance().getNotes());
	}
	
	public ArrayList<Note> findNotes(String request){
		return finder.tupleSearch(request, Notepad.getInstance().getNotes());
	}

	public String addNote(String request) {
		if(Notepad.getInstance().addNote(parser.convertStringToNote(request))){
			return "Note successfully added";
		}
		return "Note not added";
	}
	
	
	
}
