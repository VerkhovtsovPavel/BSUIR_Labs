namespace Course_project.Model.Command
{
	using System;
	using Course_project.Entity;
	using Course_project.TaskDao;
	using Course_project.Utils;
	
	public class AddPrivateTaskCommand : ICommand
	{
		public object Execute(RequestParameters parameters)
		{
			Dao.GetInstance().AddPrivateTask(this.ParseTaskParameters(parameters));
			return true;
		}
		
		private Task ParseTaskParameters(RequestParameters parameters)
		{
			Task task = new Task();
			task.StartTime = TimeUtils.DateTimeToUnixTime(parameters.GetParameter<DateTime>("StartTime"));
			task.EndTime = TimeUtils.DateTimeToUnixTime(parameters.GetParameter<DateTime>("StopTime"));
			
			task.Owner = Session.GetSession().UserName;
			task.Group = parameters.GetParameter<string>("Group");
			task.Title = parameters.GetParameter<string>("Title");
			
			return task;
		}
	}
}
