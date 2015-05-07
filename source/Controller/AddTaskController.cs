using System;
using System.Collections.Generic;
using System.Security.Cryptography;
using Course_project.Entity;
using Course_project.Model;
using Course_project.Utils;

namespace Course_project.Controller
{
	public class AddTaskController : IController
	{
	
		private AddTaskLogic addTaskLogic;

		public AddTaskController ()
		{
			addTaskLogic = new AddTaskLogic();
		}

		#region IController implementation
		public object process(string request, RequestParameters parameters)
		{
			switch(request){
				case "addHardTask":
					addTaskLogic.addHardTask(parseHardTaskParameters(parameters));
					break;
				case "addFlexibleTask":
					addTaskLogic.addFlexibleTask(parseFlexibleTaskParameters(parameters));
					break;
			}
			return null;
		}

		private Task parseHardTaskParameters(RequestParameters parameters)
		{
			Task task = new Task();
			task.StartTime = TimeUtils.DateTimeToUnixTime(parameters.getDateTime("StartTime"));
			task.EndTime = TimeUtils.DateTimeToUnixTime(parameters.getDateTime("StopTime"));
			
			task.Owner = Session.getSession().UserName;
			task.Group = parameters.getString("Group");
			task.Title = parameters.getString("Title");
			
			return task;
		}

		private Task parseFlexibleTaskParameters(RequestParameters parameters)
		{
			throw new NotImplementedException();
		}
		#endregion		
		private void addTask(){}
	}
}

