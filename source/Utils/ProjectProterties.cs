/*
 * Created by SharpDevelop.
 * User: Администратор
 * Date: 18.04.2015
 * Time: 10:57
 * 
 * To change this template use Tools | Options | Coding | Edit Standard Headers.
 */
using System;

namespace Course_project.Utils
{

	public static class ProjectProterties
	{
		public static string DB_NAME = "taskDB";
		public static string DB_SERVER = "mongodb://localhost";
		public static string DB_PATH = "./data/db";
		
		public static string USER_COLLECTION = "users";
		public static string PRIVATE_NOTES_COLLECTION = "privateNotes";
		public static string SHARED_NOTES_COLLECTION = "sharedNotes";
	}
}
