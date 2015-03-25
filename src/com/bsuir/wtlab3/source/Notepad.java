package com.bsuir.wtlab3.source;

import java.util.ArrayList;

import com.bsuir.wtlab3.entity.Note;

public class Notepad {
	private ArrayList<Note> notes = new ArrayList<Note>();
	
	private static Notepad instance;
	
	public static Notepad getInstance(){
		if(instance == null){
			instance = new Notepad();
		}
		return instance;
	}
	
	private Notepad(){};

	public ArrayList<Note> getNotes() {
		return notes;
	}
	
	public boolean addNote(Note note){
		return this.notes.add(note);
	}
}
