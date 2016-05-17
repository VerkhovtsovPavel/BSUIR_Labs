namespace Course_project.TaskDao
{
	using System;
	
	public static class Dao
	{
		public static IDao GetInstance()
		{
			return LocalMongoDBDao.GetInstance();
		}
	}
}
