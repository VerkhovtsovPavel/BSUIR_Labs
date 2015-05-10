using System;
using System.ComponentModel;
using MongoDB.Bson;
using Course_project.Utils;

namespace Course_project.Entity
{

	public class Task
	{
		[Browsable(false)]
		public ObjectId Id { get; private set; }
		public string Title { get; set; }
		public string Owner { get; set; }
		
		public string Group { get; set; }
		
		public int StartTime { get; set; }
		public int EndTime { get; set; }

		public Task(string title, string owner, string group, int startTime, int endTime)
		{
			this.Title = title;
			this.Owner = owner;
			this.Group = group;
			this.StartTime = startTime;
			this.EndTime = endTime;
		}
		
		public Task()
		{
		}
		
		public override string ToString()
		{
			return string.Format("Title={0}, Owner={1}, Group={2}, StartTime={3}, EndTime={4}, Duration={5} min", Title, Owner, Group, TimeUtils.convertUnixTimeToDateTime(StartTime), TimeUtils.convertUnixTimeToDateTime(EndTime), (EndTime-StartTime)/60);
		}
	}
}
