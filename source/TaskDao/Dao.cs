using System;

namespace Course_project.TaskDao
{
	public static class Dao
	{
		public static IDao getInstance(){
			return LocalMongoDBDao.getInstance();
		}
	}
}
