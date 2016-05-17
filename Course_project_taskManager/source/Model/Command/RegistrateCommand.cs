namespace Course_project.Model.Command
{
	using System;
	using Course_project.Entity;
	using Course_project.TaskDao;
	using Course_project.Utils;

	public class RegistrateCommand : ICommand
	{
		public object Execute(RequestParameters parameters)
		{
			string login = parameters.GetParameter<string>("Login");
			string password = parameters.GetParameter<string>("Password");
			string firstName = parameters.GetParameter<string>("FirstName");
			string lastName = parameters.GetParameter<string>("LastName");
			string timeZone = parameters.GetParameter<string>("TimeZone");
			
			if (!Dao.GetInstance().CheckUserLogin(login))
			{
				User user = new User(login, password, firstName, lastName, timeZone);
				Dao.GetInstance().AddUser(user);
				Session.CreateSession(user);
				return true;
			}
			
			return false;
		}
	}
}
