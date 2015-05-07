
using System;
using System.Collections.Generic;
using Course_project.Entity;

namespace Course_project.Storage
{	
	public class FlexibleTasksStorage
	{
		private static FlexibleTasksStorage instance;
		private List<FlexibleTask> storedTasks;
		
		
		private FlexibleTasksStorage()
		{
			this.storedTasks = new List<FlexibleTask>();
		}
		
		public static FlexibleTasksStorage getInstance(){
			if(instance == null){
				instance = new FlexibleTasksStorage();
			}
			
			return instance;
		}
		
		public void addTask(FlexibleTask task){
			this.storedTasks.Add(task);
		}
	}
}
