namespace Course_project.Model.Command
{
	using System;
	using Course_project.TaskDao;
	using Course_project.Utils;

	public class GetUserGroupsCommand : ICommand
	{
		public object Execute(RequestParameters parameters)
		{
			return Dao.GetInstance().GetUserGroups(Session.GetSession().UserName);	
		}
	}
}
