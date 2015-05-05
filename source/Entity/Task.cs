
using System;
using MongoDB.Bson;

namespace Course_project.Entity
{

	public class Task
	{
		public ObjectId Id { get; private set; }
		public string Title {get; set;}
		public string Owner {get; set;}
		
		public string Group {get; set;}
		
		public int StartTime {get; set;}
		public int EndTime {get; set;}

		public Task(string title, string owner, int startTime, int endTime){
			this.Title = title;
			this.Owner = owner;
			this.StartTime = startTime;
			this.EndTime = endTime;
		}
		
		public Task(){}
	}
}
