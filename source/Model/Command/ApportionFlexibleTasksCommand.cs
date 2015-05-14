using System;
using System.Collections.Generic;
using Course_project.Entity;
using Course_project.Storage;
using Course_project.TaskDao;
using Course_project.Utils;

namespace Course_project.Model.Command
{
	public class ApportionFlexibleTasksCommand : ICommand
	{
		private List<TimeGap> timeGaps = new List<TimeGap>();
		private Queue<FlexibleTask> taskQueue;
		
		private AddPrivateTaskCommand addTask = new AddPrivateTaskCommand();
		
		public object Execute(RequestParameters parameters)
		{
			taskQueue = FlexibleTasksStorage.getInstance().rangeFlexibleTasks();
			TimeGap allTime = FlexibleTasksStorage.getInstance().getFlexibleTasksTimeRange();
			
			timeGaps.Add(allTime);
			
			List<Task> hardTasks = Dao.getInstance().getPrivateTasksFromRange(allTime.StartTime, allTime.EndTime, Session.GetSession().UserName);
			hardTasks.Sort();
			
			excludeHardTasksTime(hardTasks);
			
			apportionTasks();
			
			return true;
		}
		
		
		/*
		 * foreach(Task)
		 if(task.DependentTasks.Contants(resolvedTask)){
			task.StartTime = Math.Max(task.StartTime, resolvedTask.EndTime)
}
		 */

		void excludeHardTasksTime(List<Task> hardTasks)
		{
			foreach (Task hardTask in hardTasks) {
				if (timeGaps[timeGaps.Count].StartTime < hardTask.StartTime) {
					timeGaps[timeGaps.Count].EndTime = hardTask.StartTime;
				}
				
				if (hardTask.StartTime < timeGaps[timeGaps.Count].StartTime) {
					timeGaps[timeGaps.Count].StartTime = hardTask.EndTime;
				} else if (timeGaps[timeGaps.Count].EndTime > hardTask.EndTime) {
					timeGaps.Add(new TimeGap(hardTask.EndTime, timeGaps[timeGaps.Count].EndTime));
				}
			}
		}

		void apportionTasks()
		{
		
			while (taskQueue.Count != 0) {
				FlexibleTask currentTask = taskQueue.Dequeue();
				
				List<TimeGap> permissibleTimeGaps = new List<TimeGap>();
				
				foreach (TimeGap timeGap in timeGaps) {
					//split and use firstly time gaps
					if (currentTask.StartTime > timeGap.StartTime && timeGap.EndTime > currentTask.EndTime) {
						permissibleTimeGaps.Add(new TimeGap(currentTask.StartTime, currentTask.EndTime));
					} else if (currentTask.StartTime < timeGap.EndTime && currentTask.EndTime > timeGap.StartTime) {
						permissibleTimeGaps.Add(new TimeGap(currentTask.StartTime, timeGap.EndTime));
					} else if (currentTask.EndTime > timeGap.StartTime && currentTask.StartTime < timeGap.EndTime) {
						permissibleTimeGaps.Add(new TimeGap(currentTask.StartTime, timeGap.EndTime));
					}
					
					
				}
				
				foreach (TimeGap timeGap in permissibleTimeGaps) {
					if (checkInterval(timeGap, currentTask)) {
						break;
					}
				}
			}
		}

		private bool checkInterval(TimeGap timeGap, FlexibleTask task)
		{
			if (timeGap.getDuration() > task.MinTimeOfOnePart) {
				if (task.isHasFreeParts()) {
					if (timeGap.getDuration() > task.RequiredTime) {
						task.RequiredTime = 0;
						task.RealParts += 1;
						addHardTask(task, timeGap.StartTime, task.RequiredTime);
						timeGap.StartTime += task.RequiredTime;
						return true;
					}	else if(timeGap.getDuration() == task.RequiredTime){
						timeGaps.Remove(timeGap);
						task.RequiredTime = 0;
						task.RealParts += 1;
						addHardTask(task, timeGap.StartTime, task.RequiredTime);
						return true;	
					
					} else {
						timeGaps.Remove(timeGap);
						task.RequiredTime -= timeGap.getDuration();
						task.RealParts += 1;
						addHardTask(task, timeGap.StartTime, timeGap.getDuration());
						return false;
					}
				}
			}
			return false;
		}
		
		void addHardTask(FlexibleTask task, int startTime, int duration)
		{
			//TODO Add start and end time
			Task newTask = new Task();
			
			newTask.Group = task.Group;
			newTask.Owner = task.Owner;
			newTask.Title = task.Title;
		}
	}
	}


