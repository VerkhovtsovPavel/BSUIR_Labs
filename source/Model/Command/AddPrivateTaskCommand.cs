using System;
using Course_project.Entity;
using Course_project.TaskDao;
using Course_project.Utils;

namespace Course_project.Model.Command
{
	public class AddPrivateTaskCommand : ICommand
	{
		public object Execute(RequestParameters parameters)
		{
			Dao.getInstance().addPrivateTask(parseTaskParameters(parameters));
			return true;
		}
		
		private Task parseTaskParameters(RequestParameters parameters)
		{
			Task task = new Task();
			task.StartTime = TimeUtils.DateTimeToUnixTime(parameters.getDateTime("StartTime"));
			task.EndTime = TimeUtils.DateTimeToUnixTime(parameters.getDateTime("StopTime"));
			
			task.Owner = Session.GetSession().UserName;
			task.Group = parameters.getString("Group");
			task.Title = parameters.getString("Title");
			
			return task;
		}
	}
}
