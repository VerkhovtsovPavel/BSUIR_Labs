using System;
using System.Collections.Generic;
using Course_project.Entity;

namespace Course_project.Model
{
	public class CalendarLogic : MainLogic
	{
		public List<Task> getTaskFromDay(string user, DateTime day){
			List<Task> taskToDay = dao.getPrivateNotesToDate(user, day);
			taskToDay.AddRange(dao.getSharedNotesToDate(day));
			
			return taskToDay;
		}
	}
}

