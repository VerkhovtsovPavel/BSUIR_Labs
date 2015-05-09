using System;
using System.Collections.Generic;
using Course_project.Entity;
using Course_project.Entity.DB;

namespace Course_project.TaskDao
{
	public interface IDao
	{
		List<Task> getPrivateTasks(string login);
		List<Task> getSharedTasks(string login);
		List<Task> getPrivateTasksFromRange(int start, int stop, string login);
		List<Task> getSharedTasksFromRange(int start, int stop, string login);
		
		List<Task> getTasksByGroup(string login, string group);
		
		User checkUser(string login, string password);
		bool CheckUserLogin(string login);
		
		void AddUser(User user);
		
		void addPrivateTask(Task note);
		void addSharedTask(Task note);

		void updateGroups(UserGroups userGroups);
		UserGroups getUserGroups(string login);
	}
}

