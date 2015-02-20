package com.bsuir.wtlab3.source.ñomparator;

import java.util.Comparator;

import com.bsuir.wtlab3.source.entity.Note;


public class NoteTopicComparator implements Comparator<Note> {

	@Override
	public int compare(Note o1, Note o2) {
		return o1.getTopic().compareToIgnoreCase(o2.getTopic());
	}
}
