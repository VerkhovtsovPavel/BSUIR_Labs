using System;
using System.Collections.Generic;
using MongoDB.Bson;

namespace Course_project.Entity.DB
{
	public class UserGroups
	{
		public ObjectId Id { get; private set; }
		public string Owner {get; set;}
		public List<String> Groups {get; set;}
		
		public UserGroups(string owner){
			this.Owner = owner;
			this.Groups = new List<string>();
		}
	}
}
