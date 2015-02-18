package ñomparator;

import java.util.Comparator;

import notepad.Note;

public class NoteEmailComparator implements Comparator<Note> {

	@Override
	public int compare(Note o1, Note o2) {
		return o1.eMail.compareToIgnoreCase(o2.eMail);
	}
}
