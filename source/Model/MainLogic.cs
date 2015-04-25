/*
 * Created by SharpDevelop.
 * User: Администратор
 * Date: 25.04.2015
 * Time: 12:27
 */
using System;
using Course_project.Dao;

namespace Course_project.Model
{
	/// <summary>
	/// Description of MainController.
	/// </summary>
	public class MainLogic
	{
		protected IDao dao = LocalMongoDBDao.getInstance();
	}
}
