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
				case "hard":
					addTaskLogic.addHardTask(parseHardTaskParameters(parameters));
					break;
				case "flexible":
					addTaskLogic.addFlexibleTask(parseFlexibleTaskParameters(parameters));
					break;
			}
			return null;
		}

		private Task parseHardTaskParameters(RequestParameters parameters)
		{
			DateTime startTime = parameters.getDateTime("StartTime");
			DateTime stopTime = parameters.getDateTime("StopTime");
			//TODO Incorrect!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			
			return new Task();
		}

		private Task parseFlexibleTaskParameters(RequestParameters parameters)
		{
			throw new NotImplementedException();
		}
		#endregion		
		private void addTask(){}
	}
}

