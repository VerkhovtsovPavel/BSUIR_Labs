package com.bsuir.wtlab3.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.bsuir.wtlab3.source.entity.Note;
import com.bsuir.wtlab3.source.ñomparator.NoteDateComparator;
import com.bsuir.wtlab3.source.ñomparator.NoteEmailComparator;
import com.bsuir.wtlab3.source.ñomparator.NoteTextComparator;
import com.bsuir.wtlab3.source.ñomparator.NoteTopicComparator;




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
