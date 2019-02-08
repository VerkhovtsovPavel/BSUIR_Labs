package com.bsuir.wtlab3.model.comparator;

import java.util.Comparator;

import com.bsuir.wtlab3.entity.Note;


public class NoteTextComparator implements Comparator<Note> {

	@Override
	public int compare(Note o1, Note o2) {
		return o1.getText().compareToIgnoreCase(o2.getText());
	}
}
