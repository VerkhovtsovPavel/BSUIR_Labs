package com.bsuir.wtlab3.dao;

import java.util.ArrayList;

import com.bsuir.wtlab3.source.entity.Note;

public interface UserDao {
		ArrayList<String> getAllNotes();
        void saveNotes(ArrayList<Note> arrayList);
}
