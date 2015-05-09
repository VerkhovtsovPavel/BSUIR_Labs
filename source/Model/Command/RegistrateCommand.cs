/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 5/7/2015
 * Time: 20:54
 */
using System;
using Course_project.Entity;
using Course_project.TaskDao;
using Course_project.Utils;

namespace Course_project.Model.Command
{
	/// <summary>
	/// Description of RegistrateCommand.
	/// </summary>
	public class RegistrateCommand : ICommand
	{
	public object Execute(RequestParameters parameters)
	{
		string login = parameters.GetParameter<String>("Login");
			string password = parameters.GetParameter<String>("Password");
			string firstName = parameters.GetParameter<String>("FirstName");
			string lastName = parameters.GetParameter<String>("LastName");;
			string timeZone = parameters.GetParameter<String>("TimeZone");;
			
			if (!Dao.getInstance().CheckUserLogin(login)) {
				User user = new User(login, password, firstName, lastName, timeZone);
				Dao.getInstance().AddUser(user);
				Session.CreateSession(user);
				return true;
			}
			return false;
	}
	}
}
