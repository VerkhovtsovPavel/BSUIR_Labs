package ñomparator;

import java.util.Comparator;

import notepad.Note;

public class NoteTopicComparator implements Comparator<Note> {

	@Override
	public int compare(Note o1, Note o2) {
		return o1.topic.compareToIgnoreCase(o2.topic);
	}
}
