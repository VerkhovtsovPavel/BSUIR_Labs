package ñomparator;

import java.util.Comparator;

import notepad.Note;

public class NoteTextComparator implements Comparator<Note> {

	@Override
	public int compare(Note o1, Note o2) {
		return o1.text.compareToIgnoreCase(o2.text);
	}
}
