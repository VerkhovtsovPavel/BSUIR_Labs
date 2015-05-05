using System;
using MongoDB.Bson;

namespace Course_project.Entity
{
	public class User
	{
		public ObjectId Id { get; private set; }
		public string Login {get; set;}
		public string Password {get; set;}
		
		public string FirstName {get; set;}
		public string LastName {get; set;}
		
		public string TimeZone {get; set;}
		
		public User(string login, string password, string firstName, string lastName, string timeZone){
			this.Login = login;
			this.Password = password;
			this.FirstName = firstName;
			this.LastName = lastName;
			this.TimeZone = timeZone;
		}
	}
}
