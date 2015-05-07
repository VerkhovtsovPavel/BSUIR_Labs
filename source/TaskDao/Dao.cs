/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 5/6/2015
 * Time: 22:05
 */
using System;

namespace Course_project.TaskDao
{
	/// <summary>
	/// Description of Dao.
	/// </summary>
	public static class Dao
	{
		public static IDao getInstance(){
			return LocalMongoDBDao.getInstance();
		}
	}
}
