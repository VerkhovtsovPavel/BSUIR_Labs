namespace Course_project.Model.Command
{
	using System;
	using Course_project.Entity.DB;
	using Course_project.TaskDao;
	using Course_project.Utils;

	public class UpdateGroupsCommand : ICommand
	{
		public object Execute(RequestParameters parameters)
		{
			UserGroups userGroups = parameters.GetParameter<UserGroups>("UserGroups");
			Dao.GetInstance().UpdateGroups(userGroups);
			return true;
		}
	}
}
