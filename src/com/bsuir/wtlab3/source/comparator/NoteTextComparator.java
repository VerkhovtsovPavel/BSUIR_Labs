package com.bsuir.wtlab3.source.comparator;

import java.util.Comparator;

import com.bsuir.wtlab3.source.entity.Note;


public class NoteTextComparator implements Comparator<Note> {

	@Override
	public int compare(Note o1, Note o2) {
		return o1.getText().compareToIgnoreCase(o2.getText());
	}
}
