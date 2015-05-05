using System;
using System.Collections.Generic;
using System.Diagnostics;
using MongoDB.Driver;
using MongoDB.Driver.Builders;
using Course_project.Entity;
using Course_project.Utils;

namespace Course_project.Dao
{
	public class LocalMongoDBDao : IDao
	{
		private static LocalMongoDBDao instance = null;
		
		private readonly MongoDatabase database;
		private Process dbServerProcess;

		private LocalMongoDBDao()
		{
			if(Process.GetProcessesByName("mongod").Length==0){
			 dbServerProcess = CommandLineCommander.executeCommand("mongod.exe --dbpath "+ProjectProterties.DB_PATH);
			}
			MongoServer server = new MongoClient(ProjectProterties.DB_SERVER).GetServer();
			database = server.GetDatabase(ProjectProterties.DB_NAME);
		}
		
		public static IDao getInstance()
		{
			if (instance == null) {
				instance = new LocalMongoDBDao();
			}
			
			return instance;
		}
 

		#region IDao implementation

		public List<Task> getPrivateNotes()
		{
			List<Task> noteList = new List<Task>();
				
			MongoCollection privateNotes = database.GetCollection<Task>(ProjectProterties.PRIVATE_NOTES_COLLECTION);
			MongoCursor<Task> privateNotesCursor = privateNotes.FindAs<Task>(Query.EQ("Login", Session.getSession().UserName));
			foreach (Task note in privateNotesCursor) {
				noteList.Add(note);
			}
			
			return noteList;
		}

		public List<Task> getSharedNotes()
		{
			List<Task> noteList = new List<Task>();
				
			MongoCollection sharedNotes = database.GetCollection<Task>(ProjectProterties.SHARED_NOTES_COLLECTION);
			MongoCursor<Task> sharedNotesCursor = sharedNotes.FindAs<Task>(Query.NE("Login", Session.getSession().UserName));
			foreach (Task note in sharedNotesCursor) {
				noteList.Add(note);
			}
			
			return noteList;
		}

		//TODO Check work
		public List<Task> getPrivateNotesFromRange(int start, int stop)
		{
			List<Task> noteList = new List<Task>();
				
			MongoCollection privateNotes = database.GetCollection<Task>(ProjectProterties.PRIVATE_NOTES_COLLECTION);
			MongoCursor<Task> privateNotesCursor = privateNotes.FindAs<Task>(Query.And(Query.EQ("Login", Session.getSession().UserName), Query.LTE("startTime", stop), Query.GTE("stopTime", start)));
			foreach (Task note in privateNotesCursor) {
				noteList.Add(note);
			}
			
			return noteList;
		}
		
		//TODO Check work
		public List<Task> getSharedNotesFromRange(int start, int stop)
		{
			List<Task> noteList = new List<Task>();
				
			MongoCollection sharedNotes = database.GetCollection<Task>(ProjectProterties.SHARED_NOTES_COLLECTION);
			MongoCursor<Task> sharedNotesCursor = sharedNotes.FindAs<Task>(Query.And(Query.LTE("startTime", stop), Query.GTE("stopTime", start), Query.NE("Login", Session.getSession().UserName)));
			foreach (Task note in sharedNotesCursor) {
				noteList.Add(note);
			}
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

		public void addUser(User user)
		{
			MongoCollection users = database.GetCollection<User>(ProjectProterties.USER_COLLECTION);
			users.Insert<User>(user);
		}

		public void addPrivateNote(Task note)
		{
			MongoCollection privateNotes = database.GetCollection<Task>(ProjectProterties.PRIVATE_NOTES_COLLECTION);
			privateNotes.Insert<Task>(note);
		}

		public void addSharedNote(Task note)
		{
			MongoCollection sharedNotes = database.GetCollection<Task>(ProjectProterties.SHARED_NOTES_COLLECTION);
			sharedNotes.Insert<Task>(note);
		}

		public User checkUser(string login, string password)
		{
			MongoCollection users = database.GetCollection<User>(ProjectProterties.USER_COLLECTION);
			MongoCursor<User> usersCursor = users.FindAs<User>(Query.And(Query.EQ("Login", login), Query.EQ("Password", password)));
			
			if (usersCursor.Size() != 0) {
				foreach (User user in usersCursor) {
					return user;
				}
			}
			
			return null;
		}

		public bool checkUserLogin(string login)
		{
			MongoCollection users = database.GetCollection<User>(ProjectProterties.USER_COLLECTION);
			MongoCursor<User> usersCursor = users.FindAs<User>(Query.EQ("Login", login));
			
			if (usersCursor.Count() != 0) {
				return true;
			}
			
			return false;
		}
		#endregion
	}
}

