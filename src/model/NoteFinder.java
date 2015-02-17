package model;

import java.util.ArrayList;

import notepad.Note;

public class NoteFinder {

	public ArrayList<Note> tupleSearch(String request){
		ArrayList<Note> searchResult = null;//All notes
		
		if(request.contains("text")){
			searchByText(searchResult);
		}
		if(request.contains("date")){
			searchByDate(searchResult);
		}
		if(request.contains("topic")){
			searchByTopic(searchResult);
		}
		if(request.contains("e-mail")){
			searchByEmail(searchResult);
		}
		
		return searchResult;
	}

	private void searchByText(ArrayList<Note> searchResult) {
		// TODO Auto-generated method stub
		
	}

	private void searchByDate(ArrayList<Note> searchResult) {
		// TODO Auto-generated method stub
		
	}

	private void searchByTopic(ArrayList<Note> searchResult) {
		// TODO Auto-generated method stub
		
	}

	private void searchByEmail(ArrayList<Note> searchResult) {
		// TODO Auto-generated method stub
		
	}
}
