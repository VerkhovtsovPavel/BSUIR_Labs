package com.bsuir.wtlab3.source.comparator;

import java.util.Comparator;

import com.bsuir.wtlab3.entity.Note;


public class NoteEmailComparator implements Comparator<Note> {

	@Override
	public int compare(Note o1, Note o2) {
		return o1.getEMail().compareToIgnoreCase(o2.getEMail());
	}
}
