package notepad;

import java.util.ArrayList;

public class Notepad {
	private ArrayList<Note> notes = new ArrayList<Note>();

	public ArrayList<Note> getNotes() {
		return notes;
	}
	
	public void addNotes(Note note){
		this.notes.add(note);
	}
}
