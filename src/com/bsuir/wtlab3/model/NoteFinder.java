package com.bsuir.wtlab3.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bsuir.wtlab3.entity.Note;

public class NoteFinder {


	@SuppressWarnings("unchecked")
	public ArrayList<Note> tupleSearch(HashMap<String, String> findParams, ArrayList<Note> allNotes) {
		ArrayList<Note> searchResult = (ArrayList<Note>) allNotes.clone();
		String param = null;
		
		if ((param = findParams.get("Text")) != null) {
			searchByText(searchResult, param);
		}
		if ((param = findParams.get("Date")) != null) {
			searchByDate(searchResult, param);
		}
		if ((param = findParams.get("Topic")) != null) {
			searchByTopic(searchResult, param);
		}
		if ((param = findParams.get("E-mail")) != null) {
			searchByEmail(searchResult, param);
		}

		return searchResult;
	}

	private void searchByText(ArrayList<Note> searchResult, String parameter) {
		ArrayList<Note> notesOnTheRemoving = new ArrayList<Note>();
		Pattern pattern = Pattern.compile(parameter);
		Matcher matcher = null;
		for (Note note : searchResult) {
			matcher = pattern.matcher(note.getText());
			if (!matcher.find()) {
				notesOnTheRemoving.add(note);
			}
		}
		searchResult.removeAll(notesOnTheRemoving);
	}

	private void searchByDate(ArrayList<Note> searchResult, String parameter) {
		ArrayList<Note> notesOnTheRemoving = new ArrayList<Note>();
		Pattern pattern = Pattern.compile(parameter);
		Matcher matcher = null;
		for (Note note : searchResult) {
			matcher = pattern.matcher(note.getCreateDate().toString());
			if (!matcher.find()) {
				notesOnTheRemoving.add(note);
			}
		}
		searchResult.removeAll(notesOnTheRemoving);
	}

	private void searchByTopic(ArrayList<Note> searchResult, String parameter) {
		ArrayList<Note> notesOnTheRemoving = new ArrayList<Note>();
		Pattern pattern = Pattern.compile(parameter);
		Matcher matcher = null;
		for (Note note : searchResult) {
			matcher = pattern.matcher(note.getTopic());
			if (!matcher.find()) {
				notesOnTheRemoving.add(note);
			}
		}
		searchResult.removeAll(notesOnTheRemoving);
	}

	private void searchByEmail(ArrayList<Note> searchResult, String parameter) {
		ArrayList<Note> notesOnTheRemoving = new ArrayList<Note>();
		Pattern pattern = Pattern.compile(parameter);
		Matcher matcher = null;
		for (Note note : searchResult) {
			matcher = pattern.matcher(note.getEMail());
			if (!matcher.find()) {
				notesOnTheRemoving.add(note);
			}
		}
		searchResult.removeAll(notesOnTheRemoving);
	}
}
