namespace Course_project.Entity
{
	using System;
	using System.Collections.Generic;
	using MongoDB.Bson;
	
	[Serializable]
	public class FlexibleTask : Task , IComparable
	{
		public FlexibleTask(
			string title,
		    string owner, 
		    string group,
		    int startTime, 
		    int endTime,
		    int requestedTime,
		    int maxPatrs, 
		    int minTimeOnOnePart, 
		    List<FlexibleTask> dependedTasks): base(title, owner, group, startTime, endTime)
		{
			this.RequiredTime = requestedTime;
			this.MaxParts = maxPatrs;
			this.MinTimeOfOnePart = minTimeOnOnePart;
			this.DependedTasks = dependedTasks;
			this.TotalDependantTasks = new List<FlexibleTask>();
			this.BuildTotalDependantTasksList(this);
		}

		public FlexibleTask()
		{
		}

		private FlexibleTask(ObjectId id, string title, string owner, string group, int startTime, int endTime, int requestedTime, int maxParts, int minTimeOfOnePart, List<FlexibleTask> dependedTasks) : this(title, owner, group, startTime, endTime, requestedTime,  maxParts, minTimeOfOnePart, dependedTasks)
		{
			this.Id = id;
		}
		
		public int MaxParts {get; set;}
		
		public int MinTimeOfOnePart {get; set;}
		
		public List<FlexibleTask> DependedTasks {get; set;}
		
		public List<FlexibleTask> TotalDependantTasks {get; set;}
		
		public int RequiredTime { get; set;}

		public int RealParts { get; set;}

		public void BuildTotalDependantTasksList(FlexibleTask task)
		{
			foreach(FlexibleTask dependanrTask in task.DependedTasks)
			{
				this.TotalDependantTasks.Add(dependanrTask);
				this.BuildTotalDependantTasksList(dependanrTask);
			}
			
			this.TotalDependantTasks = new List<FlexibleTask>(new HashSet<FlexibleTask>(this.TotalDependantTasks));
		}
		
		public bool IsHasFreeParts()
		{
			return this.MaxParts > this.RealParts;
		}

		public override int CompareTo(object obj)
		{
			return this.TotalDependantTasks.Count;
		}
		
		public override object Clone()
		{
			return new FlexibleTask(this.Id, this.Title, this.Owner, this.Group, this.StartTime, this.EndTime, this.RequiredTime, this.MaxParts, this.MinTimeOfOnePart, this.DependedTasks);
		}
		
		public bool CompareTasks(FlexibleTask other)
		{
			return this.Id == other.Id && this.Title == other.Title && this.Owner == other.Owner && this.Group == other.Group && this.StartTime == other.StartTime && this.EndTime == other.EndTime && this.MaxParts == other.MaxParts && this.MinTimeOfOnePart == other.MinTimeOfOnePart;
		}
	}
}
