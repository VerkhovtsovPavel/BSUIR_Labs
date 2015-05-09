using System;
using System.Collections.Generic;
using Course_project.Entity;
using Course_project.TaskDao;
using Course_project.Utils;

namespace Course_project.Model.Command
{
	public class GetTimeSpentByGroup : ICommand
	{
	public object Execute(RequestParameters parameters)
	{
		string group = parameters.GetParameter<String>("Group");
		
		List<Task> taskWithGroup = Dao.getInstance().getTasksByGroup(Session.GetSession().UserName, group);
		
		int timeSpent = 0;
		
		foreach(Task task in taskWithGroup){
			timeSpent+=task.EndTime-task.StartTime;
		}
		
		return timeSpent;
	}
		
	}
}
