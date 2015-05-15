namespace Course_project.Model.Command
{
	using System;
	using System.Collections.Generic;
	using Course_project.Entity;
	using Course_project.TaskDao;
	using Course_project.Utils;

	public class GetTasksFromRangeCommand : ICommand
	{
		public object Execute(RequestParameters parameters)
		{
			int startUnixTime = TimeUtils.DateTimeToUnixTime(parameters.GetParameter<DateTime>("StartTime"));
			int stopUnixTime = TimeUtils.DateTimeToUnixTime(parameters.GetParameter<DateTime>("EndTime"));
			
			List<Task> taskToDay = Dao.GetInstance().GetPrivateTasksFromRange(startUnixTime, stopUnixTime, Session.GetSession().UserName);
			taskToDay.AddRange(Dao.GetInstance().GetSharedTasksFromRange(startUnixTime, stopUnixTime, Session.GetSession().UserName));
			
			return taskToDay;
		}
	}
}
