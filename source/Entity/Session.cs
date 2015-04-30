/*
 * Created by SharpDevelop.
 * User: Администратор
 * Date: 25.04.2015
 * Time: 13:22
 */
using System;
using Course_project.Exception;

namespace Course_project
{
	/// <summary>
	/// Description of Class1.
	/// </summary>
	public class Session
	{
		private static Session instance;
		
		public string UserName {get; private set;}
		public TimeZoneInfo TimeZone {get; private set;}
		
		public static void createSession(string userName, string timeZone){
			if(instance == null){
				instance = new Session(userName, timeZone);
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
		
		private Session(string userName, string timeZone)
		{
			this.UserName = userName;
			this.TimeZone = TimeZoneInfo.FindSystemTimeZoneById(timeZone); 
		}
	}
}
