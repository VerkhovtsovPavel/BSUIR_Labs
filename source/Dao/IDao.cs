using System;
using System.Collections.Generic;
using Course_project.Entity;

namespace Course_project.Dao
{
	public interface IDao
	{
		List<Task> getPrivateNotes(string login);
		List<Task> getSharedNotes();
		List<Task> getPrivateNotesFromRange(int start, int stop);
		List<Task> getSharedNotesFromRange(int start, int stop);
		
		
		//List<User> getUsers();
		
		User checkUser(string login, string password);
		bool checkUserLogin(string login);
		
		void addUser(User user);
		
		void addPrivateNote(Task note);
		void addSharedNote(Task note);
	}
}

