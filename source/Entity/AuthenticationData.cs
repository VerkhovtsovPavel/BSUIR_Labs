using System;

namespace Course_project.Entity
{
	public class AuthenticationData
	{
		public string userName {get; set;}
		public string password {get; set;}
		
		public AuthenticationData(string userName, string password)
		{
			this.password = password;
			this.userName = userName;
		}
	}
}
