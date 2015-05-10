/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 5/7/2015
 * Time: 21:21
 */
using System;
using MongoDB.Driver;
using Course_project.Entity;
using Course_project.TaskDao;
using Course_project.Utils;

namespace Course_project.Model.Command
{
	public class ShareTaskCommand : ICommand
	{
		public object Execute(RequestParameters parameters)
		{
			try {
				Task task = parameters.GetParameter<Task>("Task");
				Dao.getInstance().addSharedTask(task);
				return true;
			} catch (MongoDuplicateKeyException) {
				return false;
			}
		}
	}
}
