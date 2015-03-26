package com.bsuir.wtlab3.dao;

import java.util.ArrayList;

import com.bsuir.wtlab3.entity.Note;
import com.bsuir.wtlab3.exception.DaoException;

public interface UserDao {
		ArrayList<String> getAllNotes() throws DaoException;
        void saveNotes(ArrayList<Note> arrayList) throws DaoException;
}
