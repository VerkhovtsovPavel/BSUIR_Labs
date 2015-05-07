/*
 * Created by SharpDevelop.
 * User: Администратор
 * Date: 25.04.2015
 * Time: 13:22
 */
using System;
using Course_project.Entity;
using Course_project.Exception;

namespace Course_project
{
	/// <summary>
	/// Description of Class1.
	/// </summary>
	public class Session
	{
		private static Session instance;
		
		private User user;
		
		public string UserName {get; private set;}
		public TimeZoneInfo TimeZone {get; private set;}
		
		public static void createSession(User user){
			if(instance == null){
				instance = new Session(user);
			}
		}
		
		public static Session getSession()
		{
			if (instance!=null) {
				return instance;
			}else{
				throw new SessionException();
			}
		}
		
		private Session(User user)
		{
			this.user = user;
			this.UserName = user.Login;
			this.TimeZone = TimeZoneInfo.FindSystemTimeZoneById(user.TimeZone); 
		}
	}
}
