using System;
using System.Collections.Generic;
using Course_project.Model;

namespace Course_project.Controller
{
	public class LoginAndRegistrationController : IController
	{
		
		private LoginAndRegistrationLogic LoginAndRegistrationLogic;
		
		public LoginAndRegistrationController ()
		{
			this.LoginAndRegistrationLogic = new LoginAndRegistrationLogic();
		}
		
		public object process(String request, Object parameters){
			switch(request){
				case "login":
					return loginUser(parameters);
				case "registrate":
					return registrateUser(parameters);
				default:
					return "Incorrect command";
			}
		}

		private bool loginUser(object parameters)
		{
			string login;
			string password;
			
			Dictionary<String, String> loginParameters = (Dictionary<String, String>) parameters;
			
			loginParameters.TryGetValue("Login",out login);
			loginParameters.TryGetValue("Password",out password);
			
			return LoginAndRegistrationLogic.loginUser(login, password);
			
		}

		private bool registrateUser(object parameters)
		{
			string login;
			string password;
			
			string firstName;
			string lastName;
			
			string timeZone;
			
			Dictionary<String, String> registrationParameters = (Dictionary<String, String>) parameters;
			
			registrationParameters.TryGetValue("Login",out login);
			registrationParameters.TryGetValue("Password",out password);
			
			registrationParameters.TryGetValue("FirstName",out firstName);
			registrationParameters.TryGetValue("LastName",out lastName);
			
			registrationParameters.TryGetValue("timeZome",out timeZone);
			
			return LoginAndRegistrationLogic.registrateUser(firstName, lastName, login, password, timeZone);

		}
	}
}

