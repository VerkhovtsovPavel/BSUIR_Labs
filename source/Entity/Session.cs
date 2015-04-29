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
		
		public void createSession(string userName){
			if(instance == null){
				instance = new Session(userName);
			}
		}
		
		public Session getSession()
		{
			if (instance!=null) {
				return instance;
			}else{
				throw new SessionException();
			}
		}
		
		private Session(string userName)
		{
			this.UserName = userName;
		}
	}
}
