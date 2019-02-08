package com.bsuir.wtlab3.model;

import java.util.ArrayList;

import com.bsuir.wtlab3.entity.Note;
import com.bsuir.wtlab3.exception.LogicException;
import com.bsuir.wtlab3.model.command.CommandName;
import com.bsuir.wtlab3.model.command.Manager;

public class NotepadLogic {
	private Manager manager;
	
	private static final String NOTE_SUCCESSFULLY_ADDED = "Note successfully added";
	private static final String NOTE_NOT_ADDED = "Note not added";
	
	private static final String NOTE_SORTED_BY_RESPONSE_FORMAT = "Notes sorted by %s";

	public NotepadLogic() {
		this.manager = new Manager();
	}

	public void getNotesFromFile() throws LogicException{
		manager.execute(CommandName.LOAD, "");
	}

	public void saveNotesToFile() throws LogicException {
		manager.execute(CommandName.SAVE, "");
	}
	
	public String sortNotes(String request) throws LogicException{
		return String.format(NOTE_SORTED_BY_RESPONSE_FORMAT, manager.execute(CommandName.SORT, request));
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Note> findNotes(String request) throws LogicException{
		return (ArrayList<Note>) manager.execute(CommandName.FIND, request);
	}

	public String addNote(String request) throws LogicException {
		if((boolean) manager.execute(CommandName.ADD, request)){
			return NOTE_SUCCESSFULLY_ADDED;
		}
		return NOTE_NOT_ADDED;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Note> getAllNotes() throws LogicException{
		return (ArrayList<Note>) manager.execute(CommandName.GET, "");
	}
	
	
	
}
