using System;
using System.Collections.Generic;
using Course_project.Dao;
using Course_project.Entity;

namespace Course_project.Model
{
	public class LoginAndRegistrationLogic : MainLogic
	{
		public bool loginUser(string login, string password)
		{
			User result = dao.checkUser(login, password);
			if(result != null){
				 Session.createSession(result);
				 return true;
			}
			return false;
		}

		public bool registrateUser(string firstName, string lastName, string login, string password, string timeZone)
		{
			if(!dao.checkUserLogin(login)){
				dao.addUser(new User(){
				            	Login = login,
				            	Password = password,
				            	FirstName = firstName,
				            	LastName = lastName,
				            	TimeZone = timeZone
				            });
				return true;
			}
			return false; //TODO Maybe use exceptions
		}
	}
}

