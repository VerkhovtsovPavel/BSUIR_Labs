using System;
using System.Collections.Generic;
using Course_project.Entity;
using Course_project.Exception;
using Course_project.Storage;
using Course_project.TaskDao;
using Course_project.Utils;

namespace Course_project.Model.Command
{
	public class ApportionFlexibleTasksCommand : ICommand
	{
		private List<TimeGap> timeGaps = new List<TimeGap>();
		private Queue<FlexibleTask> taskQueue;
		
		public object Execute(RequestParameters parameters)
		{
			//CHECK Ranking (order of tasks)
			taskQueue = FlexibleTasksStorage.getInstance().rangeFlexibleTasks();
			TimeGap allTime = FlexibleTasksStorage.getInstance().getFlexibleTasksTimeRange();
			
			timeGaps.Add(allTime);
			
			List<Task> hardTasks = Dao.getInstance().getPrivateTasksFromRange(allTime.StartTime, allTime.EndTime, Session.GetSession().UserName);
			hardTasks.Sort();
			
			excludeHardTasksTime(hardTasks);
			
			timeGaps.Sort();
			
			apportionTasks();
			
			FlexibleTasksStorage.getInstance().Clear();
			
			return true;
		}
		
		
	
		void excludeHardTasksTime(List<Task> hardTasks)
		{
			foreach (Task hardTask in hardTasks) {
				int lastElement = timeGaps.Count - 1;
				if (timeGaps[lastElement].StartTime < hardTask.StartTime) {
					timeGaps[lastElement].EndTime = hardTask.StartTime;
				}
				
				if (hardTask.StartTime < timeGaps[lastElement].StartTime) {
					timeGaps[lastElement].StartTime = hardTask.EndTime;
				} else if (timeGaps[lastElement].EndTime > hardTask.EndTime) {
					timeGaps.Add(new TimeGap(hardTask.EndTime, timeGaps[lastElement].EndTime));
				}
			}
		}

		void apportionTasks()
		{
			while (taskQueue.Count != 0) {
				FlexibleTask currentTask = taskQueue.Dequeue();
				
				List<TimeGap> permissibleTimeGaps = new List<TimeGap>();
				
				List<TimeGap> toAdd = new List<TimeGap>();
				TimeGap toRemove = null;
				
				foreach (TimeGap timeGap in timeGaps) {
					if (currentTask.StartTime >= timeGap.StartTime && timeGap.EndTime >= currentTask.EndTime) {
						toAdd.Add(new TimeGap(currentTask.EndTime, timeGap.EndTime));
						timeGap.EndTime = currentTask.StartTime;
						TimeGap permissibleTimeGap = new TimeGap(currentTask.StartTime, currentTask.EndTime);
						toAdd.Add(permissibleTimeGap);
						permissibleTimeGaps.Add(permissibleTimeGap);
					} else if (currentTask.StartTime < timeGap.StartTime && currentTask.EndTime > timeGap.EndTime) {
						permissibleTimeGaps.Add(timeGap);
					} else if (timeGap.StartTime > currentTask.StartTime && timeGap.StartTime < currentTask.EndTime) {
						TimeGap permissibleTimeGap = new TimeGap(timeGap.StartTime, currentTask.EndTime);
						permissibleTimeGaps.Add(permissibleTimeGap);
						toAdd.Add(permissibleTimeGap);
						timeGap.StartTime = currentTask.EndTime;
					} else if (timeGap.EndTime > currentTask.StartTime && timeGap.EndTime < currentTask.EndTime) {
						TimeGap permissibleTimeGap = new TimeGap(currentTask.StartTime, timeGap.EndTime);
						permissibleTimeGaps.Add(permissibleTimeGap);
						toAdd.Add(permissibleTimeGap);
						timeGap.EndTime = currentTask.StartTime;
					}
					if (timeGap.getDuration() == 0) {
							toRemove = timeGap;
					}
				}
				//CHECK if toRemove null
				timeGaps.AddRange(toAdd);
				timeGaps.Remove(toRemove);
				
				timeGaps.Sort();
				
				foreach (TimeGap timeGap in permissibleTimeGaps) {
					if (checkInterval(timeGap, currentTask)) {
						break;
					}
				}

				if (currentTask.RequiredTime > 0) {
					throw new CannotApportionTasks();
				}
				
				foreach (FlexibleTask task in FlexibleTasksStorage.getInstance().getPermissibleTasks(null).Values) {
					if (task.TotalDependantTasks.Contains(currentTask)) {
						task.StartTime = Math.Max(task.StartTime, currentTask.EndTime);
					}
				}
			}
		}
		

		private bool checkInterval(TimeGap timeGap, FlexibleTask task)
		{
			if (timeGap.getDuration() > task.MinTimeOfOnePart) {
				if (task.isHasFreeParts()) {
					if (timeGap.getDuration() > task.RequiredTime) {
						task.RealParts += 1;
						addHardTask(task, timeGap.StartTime, task.RequiredTime);
						timeGap.StartTime += task.RequiredTime;
						task.RequiredTime = 0;
						return true;
					}	else if(timeGap.getDuration() == task.RequiredTime){
						timeGaps.Remove(timeGap);
						task.RealParts += 1;
						addHardTask(task, timeGap.StartTime, task.RequiredTime);
						task.RequiredTime = 0;
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
			Task newTask = new Task();
			
			newTask.Group = task.Group;
			newTask.Owner = task.Owner;
			newTask.Title = task.Title;
			
			newTask.StartTime = startTime;
			newTask.EndTime = startTime + duration;
			
			Dao.getInstance().addPrivateTask(newTask);
		}
	}
	}


