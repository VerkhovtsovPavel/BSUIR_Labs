package com.bsuir.wtlab3.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.bsuir.wtlab3.entity.Note;

/**
 * Class contains helpful methods for files.
 * 
 * @author Pavel_Verkhovtsov
 * 
 */
public class UserFileDao implements UserDao{
	private static Logger log = Logger.getLogger(UserFileDao.class);
	private final static String fileSource = "notepad.txt";
	
	private static UserFileDao instance;
	
	public static UserFileDao getInstance(){
		if(instance == null){
			instance = new UserFileDao();
		}
		return instance;
		
	}
	
	private UserFileDao(){};

	public ArrayList<String> getAllNotes() {
		ArrayList<String> result = new ArrayList<String>();
		File file = new File(fileSource);
		try {
			BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
			log.debug("File " + file.getAbsoluteFile() + " opened");
			try {
				String lineFromFile;
				while ((lineFromFile = in.readLine()) != null) {
					if(!lineFromFile.isEmpty()){
						result.add(lineFromFile);
					}
				}

			} finally {
				in.close();
				log.debug("File " + file.getAbsoluteFile() + " closed");
			}
		} catch (IOException e) {
			log.error("Error while read file " + file.getAbsolutePath());
			log.warn("StackTrace", e);
			log.error("Notepad will be empty");
		}
		return result;
	}

	public void saveNotes(ArrayList<Note> arrayList) {
		File file = new File(fileSource);
		try {
			BufferedWriter in = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			log.debug("File " + file.getAbsoluteFile() + " opened");
			try {
				for (Note note : arrayList) {
					in.append(note.getCompressedFormat()+"\n");
				}
			} finally {
				in.close();
				log.debug("File " + file.getAbsoluteFile() + " closed");
			}
		} catch (IOException e) {
			log.error("Error while write in file " + file.getAbsolutePath());
			log.warn("StackTrace", e);
			log.error("Added notes are not saved");

		}
	}
}
