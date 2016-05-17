namespace Course_project.Model.Command
{
	using System;
	using Course_project.Entity;
	using Course_project.TaskDao;
	using Course_project.Utils;

	public class LoginCommand : ICommand
	{
		public object Execute(RequestParameters parameters)
		{
			string login = parameters.GetParameter<string>("Login");
			string password = parameters.GetParameter<string>("Password");
		
			User result = Dao.GetInstance().CheckUserIsRegistration(login, password);
			if (result != null)
			{
				Session.CreateSession(result);
				return true;
			}
			
			return false;
		}
	}
}
