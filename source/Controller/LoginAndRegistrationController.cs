using System;
using System.Collections.Generic;
using Course_project.Model;

namespace Course_project.Controller
{
	public class LoginAndRegistrationController : IController
	{
		private static LoginAndRegistrationController instance;
		
		public static LoginAndRegistrationController GetInstance(){
			if(instance == null){
				instance = new LoginAndRegistrationController();
			}
			
			return instance;
		}
		
		
		private LoginAndRegistrationLogic LoginAndRegistrationLogic;
		
		private LoginAndRegistrationController ()
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
			object login;
			object password;
			
			Dictionary<String, object> loginParameters = (Dictionary<String, object>) parameters;
			
			loginParameters.TryGetValue("Login",out login);
			loginParameters.TryGetValue("Password",out password);
			
			return LoginAndRegistrationLogic.loginUser(Convert.ToString(login), Convert.ToString(password));
			
		}

		private bool registrateUser(object parameters)
		{
			object login;
			object password;
			
			object firstName;
			object lastName;
			
			object timeZone;
			
			var registrationParameters = (Dictionary<String, object>) parameters;
			
			registrationParameters.TryGetValue("Login",out login);
			registrationParameters.TryGetValue("Password",out password);
			
			registrationParameters.TryGetValue("FirstName",out firstName);
			registrationParameters.TryGetValue("LastName",out lastName);
			
			registrationParameters.TryGetValue("timeZome",out timeZone);
			
			return LoginAndRegistrationLogic.registrateUser(Convert.ToString(firstName), Convert.ToString(lastName), Convert.ToString(login), Convert.ToString(password), Convert.ToString(timeZone));

		}
	}
}

