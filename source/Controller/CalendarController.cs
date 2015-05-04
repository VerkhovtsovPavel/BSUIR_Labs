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
					int startUnixTime;
					int stopUnixTime;
					
					((Dictionary<String, int>)parameters).TryGetValue("start",out startUnixTime);
					((Dictionary<String, int>)parameters).TryGetValue("start",out stopUnixTime);
					
					return showTaskLogic.getTaskFromRange(startUnixTime, stopUnixTime);
				default:
					return "Incorrect command";
					//TODO Exception
			}
		}

		#endregion
	}
}

