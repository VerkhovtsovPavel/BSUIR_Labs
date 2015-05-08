using System;
using Course_project.Entity.DB;
using Course_project.TaskDao;
using Course_project.Utils;

namespace Course_project.Model.Command
{

	public class UpdateGroupsCommand : ICommand
	{
		public object Execute(RequestParameters parameters)
		{
			UserGroups userGroups = parameters.GetParameter<UserGroups>("UserGroups");
			Dao.getInstance().updateGroups(userGroups);
			return true;
		}
	}
}
