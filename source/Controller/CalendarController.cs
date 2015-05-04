using System;
using System.Collections.Generic;
using Course_project.Model;
using Course_project.Utils;

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

		public object process(string request, RequestParameters parameters)
		{
			switch(request){
				case "showTasks":
					int startUnixTime = parameters.getInt("StartTime");
					int stopUnixTime = parameters.getInt("StopTime");;
					
					return showTaskLogic.getTaskFromRange(startUnixTime, stopUnixTime);
				default:
					return "Incorrect command";
					//TODO Exception
			}
		}

		#endregion
	}
}

