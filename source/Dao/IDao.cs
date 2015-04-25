using System;
using System.Collections.Generic;
using Course_project.Entity;

namespace Course_project.Dao
{
	public interface IDao
	{
		List<Task> getPrivateNotes(string login);
		List<Task> getSharedNotes();
		List<Task> getPrivateNotesToDate(string login, DateTime day);
		List<Task> getSharedNotesToDate(DateTime day);
		
		
		List<User> getUsers();
		
		bool checkUser(string login, string password);
		bool checkUserLogin(string login);
		
		void addUser(User user);
		
		void addPrivateNote(Task note);
		void addSharedNote(Task note);
	}
}

