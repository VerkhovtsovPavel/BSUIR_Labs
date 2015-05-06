using System;
using System.Collections.Generic;
using Course_project.Entity;

namespace Course_project.Model
{
	public class CalendarLogic : MainLogic
	{
		public List<Task> getTaskFromRange(int start, int stop){
			List<Task> taskToDay = dao.getPrivateNotesFromRange(start, stop, Session.getSession().UserName);
			taskToDay.AddRange(dao.getSharedNotesFromRange(start, stop, Session.getSession().UserName));
			
			return taskToDay;
		}
	}
}

