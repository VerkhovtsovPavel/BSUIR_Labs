using System;
using System.Collections.Generic;
using Course_project.Entity;

namespace Course_project.Dao
{
	public interface IDao
	{
		List<Note> getPrivateNotes(int userId);
		List<Note> getSharedNotes();
		
		List<AuthenticationData> getAuthenticationData();
		
		void addUser(AuthenticationData authData);
		
		void addPrivateNote(int userId);
		void addSharedNote(Note note);
		
	}
}

