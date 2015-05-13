namespace Course_project.Entity
{
	using System;
	using System.Collections.Generic;
	
	public class FlexibleTask : Task , IComparable
	{
		public FlexibleTask(
			string title,
		    string owner, 
		    string group,
		    int startTime, 
		    int endTime, 
		    int maxPatrs, 
		    int minTimeOnOnePart, 
		    List<FlexibleTask> dependedTasks): base(title, owner, group, startTime, endTime)
		{
			this.MaxParts = maxPatrs;
			this.MinTimeOfOnePart = minTimeOnOnePart;
			this.DependedTasks = dependedTasks;
			this.TotalDependantTasks = new List<FlexibleTask>();
			this.buildTotalDependantTasksList(this);
		}
		
		public FlexibleTask(){}
		
		public int MaxParts {get; set;}
		
		public int MinTimeOfOnePart {get; set;}
		
		public List<FlexibleTask> DependedTasks {get; set;}
		
		public List<FlexibleTask> TotalDependantTasks {get; set;}
		
		public int RequiredTime { get; set;}
				
		public void buildTotalDependantTasksList(FlexibleTask task){
			foreach(FlexibleTask dependanrTask in task.DependedTasks){
				this.TotalDependantTasks.Add(dependanrTask);
				buildTotalDependantTasksList(dependanrTask);
			}
		}

		public int CompareTo(object obj)
		{
			return TotalDependantTasks.Count;
		}

		
	}
}
