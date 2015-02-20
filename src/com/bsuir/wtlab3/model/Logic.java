package com.bsuir.wtlab3.model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bsuir.wtlab3.dao.FileDao;
import com.bsuir.wtlab3.source.Notepad;
import com.bsuir.wtlab3.source.entity.Note;

public class Logic {
	private NoteFinder finder;
	private NoteSorter sorter;
	private FileDao dao;

	public Logic() {
		this.finder = new NoteFinder();
		this.sorter = new NoteSorter();
		this.dao = new FileDao();
	}

	public void getNotesFromFile() {
		dao.getNotesFromFile();
	}

	public void saveNotesToFile() {
		dao.saveNotesToFile(Notepad.getInstance().getNotes());
	}
	
	public void sortNotes(String request){
		sorter.sortBy(parseSortMethod(request), Notepad.getInstance().getNotes());
	}
	
	public ArrayList<Note> findNotes(String request){
		return finder.tupleSearch(request, Notepad.getInstance().getNotes());
	}

	public String addNote(String request) {
		if(Notepad.getInstance().addNotes(new Note())){
			return "Note successfully added";
		}
		return "Note not added";
	}
	
	
	private String parseSortMethod(String request){
		Pattern pattern = Pattern.compile("(by)[ \\t]+\\w+");
		Matcher matcher = pattern.matcher(request);
		matcher.find();
		return matcher.group().split("[ \\t]+")[1];
	}
}
