namespace Course_project
{
	using System;
	using Course_project.Entity;
	using Course_project.Exception;

	public class Session
	{
		private static Session instance;
		
		private User user;
		
		private Session(User user)
		{
			this.user = user;
			this.UserName = user.Login;
			this.TimeZone = TimeZoneInfo.FindSystemTimeZoneById(user.TimeZone); 
		}
		
		public string UserName {get; private set;}
		
		public TimeZoneInfo TimeZone {get; private set;}
		
		public static void CreateSession(User user)
		{
			if(instance == null)
			{
				instance = new Session(user);
			}
		}
		
		public static Session GetSession()
		{
			if (instance!=null)
			{
				return instance;
			}
			else
			{
				throw new SessionException();
			}
		}
	}
}
