namespace Course_project.Storage
{
	using System;
	using System.Collections.Generic;
	using Course_project.Entity;
	using Course_project.Utils;
	
	public class FlexibleTasksStorage
	{
		private static FlexibleTasksStorage instance;
		private List<FlexibleTask> storedTasks;
		
		private FlexibleTasksStorage()
		{
			this.storedTasks = new List<FlexibleTask>();
		}
		
		public static FlexibleTasksStorage GetInstance()
		{
			if(instance == null)
			{
				instance = new FlexibleTasksStorage();
			}
			
			return instance;
		}
		
		public void AddTask(FlexibleTask task)
		{
			this.storedTasks.Add(task);
			task.TotalDependantTasks = new List<FlexibleTask>();
			task.BuildTotalDependantTasksList(task);
		}
		
		public Queue<FlexibleTask> RangeFlexibleTasks()
		{
			this.storedTasks.Sort();
			return new Queue<FlexibleTask>(this.CloneStorage());
		}
		
		public void UpdateTasksDependents()
		{
			foreach (FlexibleTask task in this.storedTasks)
			{
				task.BuildTotalDependantTasksList(task);
			}
		}
		
		public Dictionary<string, FlexibleTask> GetPermissibleTasks(FlexibleTask task)
		{
			Dictionary<string, FlexibleTask> permissibleTasks = new Dictionary<string, FlexibleTask>();
			
			foreach(FlexibleTask flexibleTask in this.storedTasks)
			{
				if(!(flexibleTask.TotalDependantTasks.Contains(task) || flexibleTask == task))
				{
					permissibleTasks.Add(flexibleTask.Title, flexibleTask);
				}
			}
			
			return permissibleTasks;
		}
		
		public TimeGap GetFlexibleTasksTimeRange()
		{
			int minStartTime = int.MaxValue;
			int maxEndTime = 0;
				
			foreach(FlexibleTask task in this.storedTasks)
			{
				if(task.StartTime < minStartTime)
				{
					minStartTime = task.StartTime;
				}
				
				if(task.EndTime > maxEndTime)
				{
					maxEndTime = task.EndTime;
				}
			}
			
			return new TimeGap(minStartTime, maxEndTime);
		}

		public void Clear()
		{
			this.storedTasks.Clear();
		}

		private List<FlexibleTask> CloneStorage()
		{
			List<FlexibleTask> storageClone = DeepCloneUtils.DeepClone<List<FlexibleTask>>(this.storedTasks);
			return storageClone;
		}
	}
}
