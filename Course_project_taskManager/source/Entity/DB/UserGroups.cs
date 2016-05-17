namespace Course_project.Entity.DB
{
	using System;
	using System.Collections.Generic;
	using MongoDB.Bson;

	public class UserGroups
	{
		public UserGroups(string owner)
		{
			this.Owner = owner;
			this.Groups = new List<string>();
		}
		
		public ObjectId Id { get; private set; }

		public string Owner {get; set;}
		
		public List<string> Groups {get; set;}
	}
}
