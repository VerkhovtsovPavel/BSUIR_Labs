namespace Course_project.TaskDao
{
	using System;
	using System.Collections.Generic;
	using System.Diagnostics;
	using MongoDB.Driver;
	using MongoDB.Driver.Builders;
	using Course_project.Entity;
	using Course_project.Entity.DB;
	using Course_project.Utils;

	public class LocalMongoDBDao : IDao
	{
		private static LocalMongoDBDao instance = null;
		
		private readonly MongoDatabase database;
		private Process dbServerProcess;

		private LocalMongoDBDao()
		{
			if(Process.GetProcessesByName("mongod").Length==0){
			  dbServerProcess = CommandLineCommander.executeCommand("mongod.exe --repair --dbpath "+ProjectProterties.DB_PATH +" & mongod.exe --dbpath "+ProjectProterties.DB_PATH);
			}
			
			MongoServer server = new MongoClient(ProjectProterties.DB_SERVER).GetServer();
			database = server.GetDatabase(ProjectProterties.DB_NAME);
		}
		
		public static IDao getInstance()
		{
			if (instance == null)
			{
				instance = new LocalMongoDBDao();
			}
			return instance;
		}
 
		public List<Task> getPrivateTasks(string login)
		{
			List<Task> noteList = new List<Task>();
				
			MongoCollection privateNotes = database.GetCollection<Task>(ProjectProterties.PRIVATE_NOTES_COLLECTION);
			MongoCursor<Task> privateNotesCursor = privateNotes.FindAs<Task>(Query.EQ("Owner", login));
			
			noteList.AddRange(privateNotesCursor);
					
			return noteList;
		}

		public List<Task> getSharedTasks(string login)
		{
			List<Task> noteList = new List<Task>();
				
			MongoCollection sharedNotes = database.GetCollection<Task>(ProjectProterties.SHARED_NOTES_COLLECTION);
			MongoCursor<Task> sharedNotesCursor = sharedNotes.FindAs<Task>(Query.NE("Owner", login));
			
			noteList.AddRange(sharedNotesCursor);
					
			return noteList;
		}
		
		public UserGroups getUserGroups(string login)
		{
			MongoCollection userGroups = database.GetCollection<UserGroups>(ProjectProterties.USER_GROUPS_COLLECTION);
			UserGroups userGroup = userGroups.FindOneAs<UserGroups>(Query.EQ("Owner", login));
			return userGroup;
		}

		public List<Task> getPrivateTasksFromRange(int start, int stop, string login)
		{
			List<Task> noteList = new List<Task>();
			
			MongoCollection privateNotes = database.GetCollection<Task>(ProjectProterties.PRIVATE_NOTES_COLLECTION);
			MongoCursor<Task> privateNotesCursor = privateNotes.FindAs<Task>(Query.And(Query.EQ("Owner", login), Query.LTE("StartTime", stop), Query.GTE("EndTime", start)));
			
			noteList.AddRange(privateNotesCursor);
						
			return noteList;
		}
		
		//TODO Check work
		public List<Task> getSharedTasksFromRange(int start, int stop, string login)
		{
			List<Task> noteList = new List<Task>();
				
			MongoCollection sharedNotes = database.GetCollection<Task>(ProjectProterties.SHARED_NOTES_COLLECTION);
			MongoCursor<Task> sharedNotesCursor = sharedNotes.FindAs<Task>(Query.And(Query.LTE("StartTime", stop), Query.GTE("EndTime", start), Query.NE("Owner", login)));
				
			noteList.AddRange(sharedNotesCursor);
			
			return noteList;
		}

		public List<Task> getTasksByGroup(string login, string group)
		{
			List<Task> noteList = new List<Task>();
				
			MongoCollection privateNotes = database.GetCollection<Task>(ProjectProterties.PRIVATE_NOTES_COLLECTION);
			MongoCursor<Task> privateNotesCursor = privateNotes.FindAs<Task>(Query.And(Query.EQ("Owner", login), Query.EQ("Group",group)));
			
			noteList.AddRange(privateNotesCursor);
			
			return noteList;
		}
		//TODO Debug method (Remove)
		/*public List<User> getUsers()
		{
			List<User> userList = new List<User>();
				
			MongoCollection users = database.GetCollection<User>(ProjectProterties.USER_COLLECTION);
			MongoCursor<User> userCursor = users.FindAllAs<User>();
			foreach (User user in userCursor) {
				userList.Add(user);
			}
			
			return userList;
			
		}
		
		public void clearUsers(){
			database.GetCollection(ProjectProterties.USER_COLLECTION).Drop();
		}*/

		public void AddUser(User user)
		{
			MongoCollection users = database.GetCollection<User>(ProjectProterties.USER_COLLECTION);
			users.Insert<User>(user);
			createUserGroups(user.Login);
		}

		public void addPrivateTask(Task note)
		{
			MongoCollection privateNotes = database.GetCollection<Task>(ProjectProterties.PRIVATE_NOTES_COLLECTION);
			privateNotes.Insert<Task>(note);
			
			
			
			//privateNotes.Update(Query.EQ("_id", note.Id), Update.Replace(note), UpdateFlags.Upsert);
		}
		
		private void createUserGroups(string login){
			MongoCollection userGroups = database.GetCollection<UserGroups>(ProjectProterties.USER_GROUPS_COLLECTION);
			UserGroups emptyUserGroup = new UserGroups(login);
			userGroups.Insert<UserGroups>(emptyUserGroup);
		}

		public void addSharedTask(Task note)
		{
			MongoCollection sharedNotes = database.GetCollection<Task>(ProjectProterties.SHARED_NOTES_COLLECTION);
			sharedNotes.Insert<Task>(note);
		}

		public void updateGroups(UserGroups userGroups)
		{
			MongoCollection userGroupsCollection = database.GetCollection<UserGroups>(ProjectProterties.USER_GROUPS_COLLECTION);
			userGroupsCollection.Update(Query.EQ("_id", userGroups.Id), Update.Replace(userGroups), UpdateFlags.Upsert);
		}
		
		public User checkUser(string login, string password)
		{
			MongoCollection users = database.GetCollection<User>(ProjectProterties.USER_COLLECTION);
			MongoCursor<User> usersCursor = users.FindAs<User>(Query.And(Query.EQ("Login", login), Query.EQ("Password", password)));
			
			if (usersCursor.Size() != 0)
			{
				foreach (User user in usersCursor) {
					return user;
				}
			}
			
			return null;
		}

		public bool CheckUserLogin(string login)
		{
			MongoCollection users = database.GetCollection<User>(ProjectProterties.USER_COLLECTION);
			MongoCursor<User> usersCursor = users.FindAs<User>(Query.EQ("Login", login));
			
			if (usersCursor.Count() != 0) {
				return true;
			}
			
			return false;
		}
	}
}

