package com.bsuir.wtlab3.model;

import java.util.ArrayList;

import com.bsuir.wtlab3.command.CommandName;
import com.bsuir.wtlab3.command.Manager;
import com.bsuir.wtlab3.entity.Note;

public class Logic {
	private Manager manager;
	
	private static final String NOTE_SUCCESSFULLY_ADDED = "Note successfully added";
	private static final String NOTE_NOT_ADDED = "Note not added";
	
	private static final String NOTE_SORTED_BY_RESPONSE_FORMAT = "Notes sorted by %s";

	public Logic() {
		this.manager = new Manager();
	}

	public void getNotesFromFile() {
		manager.execute(CommandName.LOAD, "");
	}

	public void saveNotesToFile() {
		manager.execute(CommandName.SAVE, "");
	}
	
	public String sortNotes(String request){
		return String.format(NOTE_SORTED_BY_RESPONSE_FORMAT, manager.execute(CommandName.SORT, request));
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Note> findNotes(String request){
		return (ArrayList<Note>) manager.execute(CommandName.FIND, request);
	}

	public String addNote(String request) {
		if((boolean) manager.execute(CommandName.ADD, request)){
			return NOTE_SUCCESSFULLY_ADDED;
		}
		return NOTE_NOT_ADDED;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Note> getAllNotes(){
		return (ArrayList<Note>) manager.execute(CommandName.GET, "");
	}
	
	
	
}
