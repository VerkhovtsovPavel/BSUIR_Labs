namespace Course_project.TaskDao
{
	using System;
	using System.Collections.Generic;
	using System.Diagnostics;
	using System.Threading;
	using MongoDB.Driver;
	using MongoDB.Driver.Builders;
	using Course_project.Entity;
	using Course_project.Entity.DB;
	using Course_project.Utils;

	public class LocalMongoDBDao : IDao
	{
		private static LocalMongoDBDao instance = null;
		
		private readonly MongoDatabase database;

		private LocalMongoDBDao()
		{
			if(Process.GetProcessesByName("mongod").Length==0)
			{
				CommandLineCommander.ExecuteCommand("mongod.exe --repair --dbpath "+ProjectProterties.DB_PATH).WaitForExit();
				CommandLineCommander.ExecuteCommand("mongod.exe --dbpath "+ProjectProterties.DB_PATH);
			  	Thread.Sleep(5000);
			}
			
			MongoServer server = new MongoClient(ProjectProterties.DB_SERVER).GetServer();
			this.database = server.GetDatabase(ProjectProterties.DB_NAME);
		}
		
		public static IDao GetInstance()
		{
			if (instance == null)
			{
				instance = new LocalMongoDBDao();
			}
			
			return instance;
		}
 
		public List<Task> GetPrivateTasks(string login)
		{
			List<Task> noteList = new List<Task>();
				
			MongoCollection privateNotes = this.database.GetCollection<Task>(ProjectProterties.PRIVATE_NOTES_COLLECTION);
			MongoCursor<Task> privateNotesCursor = privateNotes.FindAs<Task>(Query.EQ("Owner", login));
			
			noteList.AddRange(privateNotesCursor);
					
			return noteList;
		}

		public List<Task> GetSharedTasks(string login)
		{
			List<Task> noteList = new List<Task>();
				
			MongoCollection sharedNotes = this.database.GetCollection<Task>(ProjectProterties.SHARED_NOTES_COLLECTION);
			MongoCursor<Task> sharedNotesCursor = sharedNotes.FindAs<Task>(Query.NE("Owner", login));
			
			noteList.AddRange(sharedNotesCursor);
					
			return noteList;
		}
		
		public UserGroups GetUserGroups(string login)
		{
			MongoCollection userGroups = this.database.GetCollection<UserGroups>(ProjectProterties.USER_GROUPS_COLLECTION);
			UserGroups userGroup = userGroups.FindOneAs<UserGroups>(Query.EQ("Owner", login));
			return userGroup;
		}

		public List<Task> GetPrivateTasksFromRange(int start, int stop, string login)
		{
			List<Task> noteList = new List<Task>();
			
			MongoCollection privateNotes = this.database.GetCollection<Task>(ProjectProterties.PRIVATE_NOTES_COLLECTION);
			MongoCursor<Task> privateNotesCursor = privateNotes.FindAs<Task>(Query.And(Query.EQ("Owner", login), Query.LTE("StartTime", stop), Query.GTE("EndTime", start)));
			
			noteList.AddRange(privateNotesCursor);
						
			return noteList;
		}
		
		public List<Task> GetSharedTasksFromRange(int start, int stop, string login)
		{
			List<Task> noteList = new List<Task>();
				
			MongoCollection sharedNotes = this.database.GetCollection<Task>(ProjectProterties.SHARED_NOTES_COLLECTION);
			MongoCursor<Task> sharedNotesCursor = sharedNotes.FindAs<Task>(Query.And(Query.LTE("StartTime", stop), Query.GTE("EndTime", start), Query.NE("Owner", login)));
				
			noteList.AddRange(sharedNotesCursor);
			
			return noteList;
		}

		public List<Task> GetTasksByGroup(string login, string group)
		{
			List<Task> noteList = new List<Task>();
				
			MongoCollection privateNotes = this.database.GetCollection<Task>(ProjectProterties.PRIVATE_NOTES_COLLECTION);
			MongoCursor<Task> privateNotesCursor = privateNotes.FindAs<Task>(Query.And(Query.EQ("Owner", login), Query.EQ("Group",group)));
			
			noteList.AddRange(privateNotesCursor);
			
			return noteList;
		}

		public void RemoveTask(Task task)
		{
			MongoCollection privateNotes = this.database.GetCollection<Task>(ProjectProterties.PRIVATE_NOTES_COLLECTION);
			privateNotes.Remove(Query.EQ("_id",task.Id));
			
			MongoCollection sharedNotes = this.database.GetCollection<Task>(ProjectProterties.SHARED_NOTES_COLLECTION);
			sharedNotes.Remove(Query.EQ("_id",task.Id));
		}

		public void AddUser(User user)
		{
			MongoCollection users = this.database.GetCollection<User>(ProjectProterties.USER_COLLECTION);
			users.Insert<User>(user);
			this.CreateUserGroups(user.Login);
		}

		public void AddPrivateTask(Task note)
		{
			MongoCollection privateNotes = this.database.GetCollection<Task>(ProjectProterties.PRIVATE_NOTES_COLLECTION);
			privateNotes.Insert<Task>(note);
		}

		public void UpdateTask(Task task)
		{
			MongoCollection privateNotes = this.database.GetCollection<Task>(ProjectProterties.PRIVATE_NOTES_COLLECTION);
			privateNotes.Update(Query.EQ("_id", task.Id), Update.Replace(task), UpdateFlags.Upsert);
		}
		
		public void AddSharedTask(Task note)
		{
			MongoCollection sharedNotes = this.database.GetCollection<Task>(ProjectProterties.SHARED_NOTES_COLLECTION);
			sharedNotes.Insert<Task>(note);
		}

		public void UpdateGroups(UserGroups userGroups)
		{
			MongoCollection userGroupsCollection = this.database.GetCollection<UserGroups>(ProjectProterties.USER_GROUPS_COLLECTION);
			userGroupsCollection.Update(Query.EQ("_id", userGroups.Id), Update.Replace(userGroups), UpdateFlags.Upsert);
		}
		
		public User CheckUserIsRegistration(string login, string password)
		{
			MongoCollection users = this.database.GetCollection<User>(ProjectProterties.USER_COLLECTION);
			MongoCursor<User> usersCursor = users.FindAs<User>(Query.And(Query.EQ("Login", login), Query.EQ("Password", password)));
			
			if (usersCursor.Size() != 0)
			{
				foreach (User user in usersCursor)
				{
					return user;
				}
			}
			
			return null;
		}

		public bool CheckUserLogin(string login)
		{
			MongoCollection users = this.database.GetCollection<User>(ProjectProterties.USER_COLLECTION);
			MongoCursor<User> usersCursor = users.FindAs<User>(Query.EQ("Login", login));
			
			if (usersCursor.Count() != 0)
			{
				return true;
			}
			
			return false;
		}
		
		private void CreateUserGroups(string login)
		{
			MongoCollection userGroups = this.database.GetCollection<UserGroups>(ProjectProterties.USER_GROUPS_COLLECTION);
			UserGroups emptyUserGroup = new UserGroups(login);
			userGroups.Insert<UserGroups>(emptyUserGroup);
		}
	}
}