using System;
using Course_project.Entity;
using Course_project.TaskDao;
using Course_project.Utils;

namespace Course_project.Model.Command
{
	public class LoginCommand : ICommand
	{
	public object Execute(RequestParameters parameters)
	{
		string login = parameters.GetParameter<String>("Login");
		string password = parameters.GetParameter<String>("Password");
		
		User result = Dao.getInstance().checkUser(login, password);
			if (result != null) {
				Session.CreateSession(result);
				return true;
			}
			return false;
	}
		
	}
}
