using System;
using System.Collections.Generic;
using Course_project.Dao;
using Course_project.Entity;

namespace Course_project.Model
{
	public class AddTaskLogic : MainLogic
	{
		
		private List<Task> flexibleTasks = new List<Task>();

		public void addHardTask(Task task) 
		{
			/*if(task.isShare())*/
			dao.addPrivateNote(task);
			
		}

		public void addFlexibleTask(Task task)
		{
			flexibleTasks.Add(task);
		}
	}
}

