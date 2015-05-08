using System;
using Course_project.TaskDao;
using Course_project.Utils;

namespace Course_project.Model.Command
{
	public class GetUserGroupsCommand : ICommand
	{
		public object Execute(RequestParameters parameters)
		{
			return Dao.getInstance().getUserGroups(Session.GetSession().UserName);	
		}
	}
}
