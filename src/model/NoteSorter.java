package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ñomparator.NoteDateComparator;
import ñomparator.NoteEmailComparator;
import ñomparator.NoteTextComparator;
import ñomparator.NoteTopicComparator;


import notepad.Note;

public class NoteSorter {
	private Comparator<Note> comparator;
	
	public void sortBy(String method, ArrayList<Note> notepad){
		switch (method){
		case "date" : 
			comparator = new NoteDateComparator();
			break;
		case "e-mail" : 
			comparator = new NoteEmailComparator();
			break;
		case "topic" : 
			comparator = new NoteTopicComparator();
			break;
		case "text" : 
			comparator = new NoteTextComparator();
			break;	
		}
		Collections.sort(notepad, comparator);
	}
	
}
