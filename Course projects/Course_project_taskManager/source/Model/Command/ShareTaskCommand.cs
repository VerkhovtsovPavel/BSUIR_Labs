namespace Course_project.Model.Command
{
	using System;
	using MongoDB.Driver;
	using Course_project.Entity;
	using Course_project.TaskDao;
	using Course_project.Utils;

	public class ShareTaskCommand : ICommand
	{
		public object Execute(RequestParameters parameters)
		{
			try
			{
				Task task = parameters.GetParameter<Task>("Task");
				Dao.GetInstance().AddSharedTask(task);
				return true;
			}
			catch (MongoDuplicateKeyException)
			{
				return false;
			}
		}
	}
}
