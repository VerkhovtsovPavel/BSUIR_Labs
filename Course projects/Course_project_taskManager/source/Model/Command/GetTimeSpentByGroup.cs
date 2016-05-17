namespace Course_project.Model.Command
{
	using System;
	using System.Collections.Generic;
	using Course_project.Entity;
	using Course_project.TaskDao;
	using Course_project.Utils;

	public class GetTimeSpentByGroup : ICommand
	{
		public object Execute(RequestParameters parameters)
		{
			string group = parameters.GetParameter<string>("Group");
			
			List<Task> taskWithGroup = Dao.GetInstance().GetTasksByGroup(Session.GetSession().UserName, group);
			
			int timeSpent = 0;
			
			foreach(Task task in taskWithGroup)
			{
				timeSpent+=task.EndTime-task.StartTime;
			}
			
			return timeSpent;
		}
	}
}
