﻿namespace Course_project.Model.Command
{
	using System;
	using Course_project.Entity;
	using Course_project.TaskDao;
	using Course_project.Utils;

	public class UpdateTaskCommand : ICommand
	{
		public object Execute(RequestParameters parameters)
		{
			Task task = parameters.GetParameter<Task>("Task");
			
			if(Session.GetSession().UserName!=task.Owner)
			{
				return false;
			}
			
			Dao.GetInstance().UpdateTask(task);
			return true;
		}
	}
}
