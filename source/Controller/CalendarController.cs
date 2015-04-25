using System;
using System.Collections.Generic;
using Course_project.Model;

namespace Course_project.Controller
{
	public class CalendarController : IController
	{
		private CalendarLogic showTaskLogic;
		
		public CalendarController ()
		{
			showTaskLogic = new CalendarLogic();
		}

		#region IController implementation

		public object process(string request, object parameters)
		{
			switch(request){
				case "showTasks":
					DateTime day;
					String user;
					((Dictionary<String, DateTime>)parameters).TryGetValue("date",out day);
					((Dictionary<String, String>)parameters).TryGetValue("date",out user);
					return showTaskLogic.getTaskFromDay(user, day);
				default:
					return "Incorrect command";
					//TODO Exception
			}
		}

		#endregion
	}
}

