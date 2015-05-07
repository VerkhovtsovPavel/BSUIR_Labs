using System;
using System.Collections.Generic;
using Course_project.Model;
using Course_project.Utils;

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
		
		public object process(String request, RequestParameters parameters){
			switch(request){
				case "login":
					return loginUser(parameters);
				case "registrate":
					return registrateUser(parameters);
				default:
					return "Incorrect command";
			}
		}

		private bool loginUser(RequestParameters parameters)
		{
			string login = parameters.getString("Login");
			string password = parameters.getString("Password");

			return LoginAndRegistrationLogic.loginUser(login, password);			
		}

		private bool registrateUser(RequestParameters parameters)
		{
			string login = parameters.getString("Login");
			string password = parameters.getString("Password");
			string firstName = parameters.getString("FirstName");
			string lastName = parameters.getString("LastName");;
			string timeZone = parameters.getString("TimeZone");;
			
			return LoginAndRegistrationLogic.registrateUser(firstName, lastName, login, password, timeZone);

		}
	}
}

