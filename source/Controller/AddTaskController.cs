using System;
using System.Collections.Generic;
using System.Security.Cryptography;
using Course_project.Entity;
using Course_project.Model;

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
		public object process(string request, object parameters)
		{
			switch(request){
				case "hard":
					addTaskLogic.addHardTask(parseHardTaskParameters(parameters));
					break;
				case "flexible":
					addTaskLogic.addFlexibleTask(parseFlexibleTaskParameters(parameters));
					break;
			}
			return null;
		}

		private Task parseHardTaskParameters(object parameters)
		{
			DateTime startTime;
			DateTime stopTime;
			((Dictionary<String, DateTime>)parameters).TryGetValue("startTime", out startTime);
			((Dictionary<String, DateTime>)parameters).TryGetValue("stopTime", out stopTime);
			
			return new Task();
		}

		private Task parseFlexibleTaskParameters(object parameters)
		{
			throw new NotImplementedException();
		}
		#endregion		
		private void addTask(){}
	}
}

