package com.bsuir.wtlab3.model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bsuir.wtlab3.source.entity.Note;

public class NoteFinder {

	@SuppressWarnings("unchecked")
	public ArrayList<Note> tupleSearch(String request, ArrayList<Note> allNotes) {
		ArrayList<Note> searchResult = (ArrayList<Note>) allNotes.clone();

		if (request.contains("text")) {
			searchByText(searchResult, request);
		}
		if (request.contains("date")) {
			searchByDate(searchResult);
		}
		if (request.contains("topic")) {
			searchByTopic(searchResult);
		}
		if (request.contains("e-mail")) {
			searchByEmail(searchResult);
		}

		return searchResult;
	}

	private void searchByText(ArrayList<Note> searchResult, String parameter) {
		ArrayList<Note> notesOnTheRemoving = new ArrayList<Note>();
		Pattern pattern = Pattern.compile("");
		Matcher matcher = null;
		for (Note note : searchResult) {
			matcher = pattern.matcher(note.getText());
			if (!matcher.find()) {
				notesOnTheRemoving.add(note);
			}
		}
		searchResult.removeAll(notesOnTheRemoving);
	}

	private void searchByDate(ArrayList<Note> searchResult) {
		ArrayList<Note> notesOnTheRemoving = new ArrayList<Note>();
		Pattern pattern = Pattern.compile("");
		Matcher matcher = null;
		for (Note note : searchResult) {
			matcher = pattern.matcher(note.getCreateDate().toString());
			if (!matcher.find()) {
				notesOnTheRemoving.add(note);
			}
		}
		searchResult.removeAll(notesOnTheRemoving);
	}

	private void searchByTopic(ArrayList<Note> searchResult) {
		ArrayList<Note> notesOnTheRemoving = new ArrayList<Note>();
		Pattern pattern = Pattern.compile("");
		Matcher matcher = null;
		for (Note note : searchResult) {
			matcher = pattern.matcher(note.getTopic());
			if (!matcher.find()) {
				notesOnTheRemoving.add(note);
			}
		}
		searchResult.removeAll(notesOnTheRemoving);
	}

	private void searchByEmail(ArrayList<Note> searchResult) {
		ArrayList<Note> notesOnTheRemoving = new ArrayList<Note>();
		Pattern pattern = Pattern.compile("");
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
