namespace Course_project.Model.Command
{
	using System;
	using System.Collections.Generic;
	using Course_project.Controller;
	using Course_project.Entity;
	using Course_project.Exception;
	using Course_project.Storage;
	using Course_project.TaskDao;
	using Course_project.Utils;

	public class ApportionFlexibleTasksCommand : ICommand
	{
		private List<TimeGap> timeGaps = new List<TimeGap>();
		private Queue<FlexibleTask> taskQueue;
		
		public object Execute(RequestParameters parameters)
		{
			this.taskQueue = FlexibleTasksStorage.GetInstance().RangeFlexibleTasks();
			TimeGap allTime = FlexibleTasksStorage.GetInstance().GetFlexibleTasksTimeRange();
			
			this.timeGaps.Add(allTime);
			
			List<Task> hardTasks = Dao.GetInstance().GetPrivateTasksFromRange(allTime.StartTime, allTime.EndTime, Session.GetSession().UserName);
			hardTasks.Sort();
			
			this.ExcludeHardTasksTime(hardTasks);
			
			this.timeGaps.Sort();
			
			this.ApportionTasks();
			
			FlexibleTasksStorage.GetInstance().Clear();
			
			return true;
		}
		
		private void ExcludeHardTasksTime(List<Task> hardTasks)
		{
			foreach (Task hardTask in hardTasks)
			{
				int lastElement = this.timeGaps.Count - 1;
				if (this.timeGaps[lastElement].StartTime < hardTask.StartTime)
				{
					this.timeGaps[lastElement].EndTime = hardTask.StartTime;
				}
				
				if (hardTask.StartTime < this.timeGaps[lastElement].StartTime)
				{
					this.timeGaps[lastElement].StartTime = hardTask.EndTime;
				}
				else if (this.timeGaps[lastElement].EndTime > hardTask.EndTime)
				{
					this.timeGaps.Add(new TimeGap(hardTask.EndTime, this.timeGaps[lastElement].EndTime));
				}
			}
		}

		private void ApportionTasks()
		{
			while (this.taskQueue.Count != 0)
			{
				FlexibleTask currentTask = this.taskQueue.Dequeue();
				
				List<TimeGap> permissibleTimeGaps = new List<TimeGap>();
				
				List<TimeGap> toAdd = new List<TimeGap>();
				List<TimeGap> toRemove = new List<TimeGap>();
				
				foreach (TimeGap timeGap in this.timeGaps)
				{
					if (currentTask.StartTime >= timeGap.StartTime && timeGap.EndTime >= currentTask.EndTime)
					{
						toAdd.Add(new TimeGap(currentTask.EndTime, timeGap.EndTime));
						timeGap.EndTime = currentTask.StartTime;
						TimeGap permissibleTimeGap = new TimeGap(currentTask.StartTime, currentTask.EndTime);
						toAdd.Add(permissibleTimeGap);
						permissibleTimeGaps.Add(permissibleTimeGap);
					}
					else if (currentTask.StartTime < timeGap.StartTime && currentTask.EndTime > timeGap.EndTime)
					{
						permissibleTimeGaps.Add(timeGap);
					}
					else if (timeGap.StartTime > currentTask.StartTime && timeGap.StartTime < currentTask.EndTime)
					{
						TimeGap permissibleTimeGap = new TimeGap(timeGap.StartTime, currentTask.EndTime);
						permissibleTimeGaps.Add(permissibleTimeGap);
						toAdd.Add(permissibleTimeGap);
						timeGap.StartTime = currentTask.EndTime;
					}
					else if (timeGap.EndTime > currentTask.StartTime && timeGap.EndTime < currentTask.EndTime)
					{
						TimeGap permissibleTimeGap = new TimeGap(currentTask.StartTime, timeGap.EndTime);
						permissibleTimeGaps.Add(permissibleTimeGap);
						toAdd.Add(permissibleTimeGap);
						timeGap.EndTime = currentTask.StartTime;
					}
					
					if (timeGap.GetDuration() == 0)
					{
						toRemove.Add(timeGap);
					}
				}
				
				this.timeGaps.AddRange(toAdd);
				
				foreach (TimeGap task in toRemove)
				{
					this.timeGaps.Remove(task);
				}
				
				this.timeGaps.Sort();
				
				foreach (TimeGap timeGap in permissibleTimeGaps)
				{
					if (this.CheckInterval(timeGap, currentTask))
					{
						break;
					}
				}

				if (currentTask.RequiredTime > 0)
				{
					throw new CannotApportionTasks();
				}
				
				foreach (FlexibleTask task in FlexibleTasksStorage.GetInstance().GetPermissibleTasks(null).Values)
				{
						if (task.DependedTasks.Contains(currentTask))
						{
							task.StartTime = Math.Max(task.StartTime, currentTask.EndTime);
						}
					}
				}
			}
		
		private bool CheckInterval(TimeGap timeGap, FlexibleTask task)
		{
			if (timeGap.GetDuration() > task.MinTimeOfOnePart)
			{
				if (task.IsHasFreeParts())
				{
					if (timeGap.GetDuration() > task.RequiredTime)
					{
						task.RealParts += 1;
						task.EndTime = timeGap.StartTime + task.RequiredTime;
						this.AddHardTask(task, timeGap.StartTime, task.RequiredTime);
						timeGap.StartTime += task.RequiredTime;
						task.RequiredTime = 0;
						return true;
					}
					else if(timeGap.GetDuration() == task.RequiredTime)
					{
						this.timeGaps.Remove(timeGap);
						task.RealParts += 1;
						task.EndTime = timeGap.StartTime + task.RequiredTime;
						this.AddHardTask(task, timeGap.StartTime, task.RequiredTime);
						task.RequiredTime = 0;
						return true;	
					}
					else
					{
						this.timeGaps.Remove(timeGap);
						task.RequiredTime -= timeGap.GetDuration();
						task.RealParts += 1;
						this.AddHardTask(task, timeGap.StartTime, timeGap.GetDuration());
						return false;
					}
				}
			}
			
			return false;
		}
		
		private void AddHardTask(FlexibleTask task, int startTime, int duration)
		{
			Task newTask = new Task();
			
			newTask.Group = task.Group;
			newTask.Owner = task.Owner;
			newTask.Title = task.Title;
			
			newTask.StartTime = startTime;
			newTask.EndTime = startTime + duration;
			
			Dao.GetInstance().AddPrivateTask(newTask);
		}
	}
}