using System;
using Course_project.Entity;
using Course_project.TaskDao;
using Course_project.Utils;

namespace Course_project.Model.Command
{
	public class RemoveTaskCommand : ICommand
	{

	public object Execute(RequestParameters parameters)
	{
		Task task = parameters.GetParameter<Task>("Task");
		
		Dao.getInstance().removeTask(task);
		
		return true;
	}



	}
}
