using System;
using System.Collections.Generic;
using Course_project.Entity;

namespace Course_project.Dao
{
	public class LocalMongoDBDao : IDao
	{
		public LocalMongoDBDao ()
		{
		}

		#region IDao implementation

		public List<Note> getPrivateNotes(int userId)
		{
			throw new NotImplementedException();
		}

		public List<Note> getSharedNotes()
		{
			throw new NotImplementedException();
		}

		public List<AuthenticationData> getAuthenticationData()
		{
			throw new NotImplementedException();
		}

		public void addUser(AuthenticationData authData)
		{
			throw new NotImplementedException();
		}

		public void addPrivateNote(int userId)
		{
			throw new NotImplementedException();
		}

		public void addSharedNote(Note note)
		{
			throw new NotImplementedException();
		}

		#endregion
	}
}

