namespace Course_project.Entity
{
	using System;
	using System.Collections.Generic;
	
	public class FlexibleTask : Task
	{
		public FlexibleTask(
			string title,
		    string owner, 
		    int startTime, 
		    int endTime, 
		    int maxPatrs, 
		    int minTimeOnOnePart, 
		    List<Task> dependedTasks): base(title, owner,  startTime, endTime)
		{
			this.MaxParts = maxPatrs;
			this.MinTimeOfOnePart = minTimeOnOnePart;
			this.DependedTasks = dependedTasks;
		}
		
		public int MaxParts {get; set;}
		
		public int MinTimeOfOnePart {get; set;}
		
		public List<Task> DependedTasks {get; set;}
	}
}
