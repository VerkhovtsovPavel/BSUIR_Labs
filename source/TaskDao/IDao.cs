namespace Course_project.TaskDao
{
	using System;
	using System.Collections.Generic;
	using Course_project.Entity;
	using Course_project.Entity.DB;

	public interface IDao
	{
		List<Task> GetPrivateTasks(string login);
		
		List<Task> GetSharedTasks(string login);
		
		List<Task> GetPrivateTasksFromRange(int start, int stop, string login);
		
		List<Task> GetSharedTasksFromRange(int start, int stop, string login);
		
		List<Task> GetTasksByGroup(string login, string group);

		void RemoveTask(Task task);
		
		User CheckUserIsRegistration(string login, string password);
		
		bool CheckUserLogin(string login);
		
		void AddUser(User user);
		
		void AddPrivateTask(Task task);
		
		void AddSharedTask(Task task);

		void UpdateGroups(UserGroups userGroups);
		
		void UpdateTask(Task task);
		
		UserGroups GetUserGroups(string login);
	}
}