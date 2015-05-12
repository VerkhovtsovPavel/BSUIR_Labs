
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
			task.buildTotalDependantTasksList(task);
		}
		
		public Queue<FlexibleTask> rangeFlexibleTasks(){
			storedTasks.Sort();
			return new Queue<FlexibleTask>(storedTasks);
		}
		
		//TODO Check with null
		public Dictionary<String, FlexibleTask> getPermissibleTasks(FlexibleTask task){
			Dictionary<String, FlexibleTask> permissibleTasks = new Dictionary<String, FlexibleTask>();
			
			foreach(FlexibleTask flexibleTask in storedTasks){
				if(!flexibleTask.TotalDependantTasks.Contains(task)){
					permissibleTasks.Add(flexibleTask.Title, flexibleTask);
				}
			}
			
			return permissibleTasks;
		}
	}
	
}
