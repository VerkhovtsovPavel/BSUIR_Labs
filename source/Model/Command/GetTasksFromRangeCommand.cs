using System;
using System.Collections.Generic;
using Course_project.Entity;
using Course_project.TaskDao;
using Course_project.Utils;

namespace Course_project.Model.Command
{

	public class GetTasksFromRangeCommand : ICommand
	{
		public object Execute(RequestParameters parameters)
		{
			int startUnixTime = TimeUtils.DateTimeToUnixTime(parameters.GetParameter<DateTime>("StartTime"));
			int stopUnixTime = TimeUtils.DateTimeToUnixTime(parameters.GetParameter<DateTime>("EndTime"));
			
			List<Task> taskToDay = Dao.getInstance().getPrivateTasksFromRange(startUnixTime, stopUnixTime, Session.GetSession().UserName);
			taskToDay.AddRange(Dao.getInstance().getSharedTasksFromRange(startUnixTime, stopUnixTime, Session.GetSession().UserName));
			
			return taskToDay;
		}
	}
}
