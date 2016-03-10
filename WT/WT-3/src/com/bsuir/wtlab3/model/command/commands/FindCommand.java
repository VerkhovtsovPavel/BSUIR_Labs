package com.bsuir.wtlab3.model.command.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.bsuir.wtlab3.entity.Note;
import com.bsuir.wtlab3.model.command.Command;
import com.bsuir.wtlab3.source.Notepad;

public class FindCommand implements Command{
	private final Logger log = Logger.getLogger(FindCommand.class);
	
	private static final String FIND_TYPE_REGEXP = "\\-%s[\\t ]+[\\w\\W]+";
	private static final String SEARCH_PARAMETERS_DELIMETER_REGEXP = "[\\t ]+\""; 

	@Override
	public Object execute(String request) {
		log.debug("Execute find command");
		return tupleSearch(parseSearchParameters(request), Notepad.getInstance().getNotes());
	}
	

	@SuppressWarnings("unchecked")
	private ArrayList<Note> tupleSearch(HashMap<String, String> findParams, ArrayList<Note> allNotes) {
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

	private HashMap<String, String> parseSearchParameters(String request) {
		HashMap<String, String> result = new HashMap<>();
		result.put("Topic", getSearchParameter("t", request));
		result.put("Text", getSearchParameter("m", request));
		result.put("Date", getSearchParameter("d", request));
		result.put("E-mail", getSearchParameter("e", request));
		
		return result;
	}
	
	private String getSearchParameter(String searchType, String request){
		Pattern pattern = Pattern.compile(String.format(FIND_TYPE_REGEXP,searchType));
		Matcher matcher = pattern.matcher(request);	
		if(matcher.find()){
			return matcher.group().split(SEARCH_PARAMETERS_DELIMETER_REGEXP)[1].replace("\"", "");
		}
		return null;
	}


}
