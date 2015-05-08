using System;
using System.Collections.Generic;
using Course_project.Entity;
using Course_project.Entity.DB;


//TODO Refactor using parametraze methods

namespace Course_project.Utils
{
	public class RequestParameters
	{
		private Dictionary<String, Object> parameters;
		
		public RequestParameters()
		{
			this.parameters = new Dictionary<String, Object>();
		}
	
		#region Uncheck
		public void AddParameter<T>(string key, T value)
		{
			parameters.Add(key, value);	
		}
		
		public T GetParameter<T>(string key)
		{
			object value;
			parameters.TryGetValue(key, out value);
			return (T)Convert.ChangeType(value, typeof(T));
		}
		#endregion
		
		
		public void addInt(string key, int value)
		{
			parameters.Add(key, value);	
		}
		
		public void addString(string key, string value)
		{
			parameters.Add(key, value);	
		}
		
		public void addDateTime(string key, DateTime value)
		{
			parameters.Add(key, value);	
		}

		public void addTask(string key, Task value)
		{
			parameters.Add(key, value);	
		}
		
		public void addTaskList(string key, List<Task> value)
		{
			parameters.Add(key, value);	
		}

		public void addUserGroups(string key, UserGroups value)
		{
			parameters.Add(key, value);
		}
		public int getInt(string key)
		{
			object value;
			parameters.TryGetValue(key, out value);
			
			return Convert.ToInt32(value);
		}
		
		public string getString(string key)
		{
			object value;
			parameters.TryGetValue(key, out value);
			
			return Convert.ToString(value);
		}
		
		public DateTime getDateTime(string key)
		{
			object value;
			parameters.TryGetValue(key, out value);
			
			return Convert.ToDateTime(value);
		}

		//TODO Check cost work!
		public Task getTask(string key)
		{
			object value;
			parameters.TryGetValue(key, out value);
			
			return (Task)value;
		}
		//TODO Check cost work!
		public List<Task> getTaskList(string key)
		{
			object value;
			parameters.TryGetValue(key, out value);
			
			return (List<Task>)value;
		}
		
		public UserGroups getUserGroups(string key)
		{
			object value;
			parameters.TryGetValue(key, out value);
			
			return (UserGroups)value;
		
		}
	}
}
