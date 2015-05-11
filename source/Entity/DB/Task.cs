using System;
using System.ComponentModel;
using MongoDB.Bson;
using Course_project.Utils;

namespace Course_project.Entity
{

	public class Task : ICloneable
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
		
		private Task(ObjectId Id, string title, string owner, string group, int startTime, int endTime){
			this.Id = Id;
			this.Title = title;
			this.Owner = owner;
			this.Group = group;
			this.StartTime = startTime;
			this.EndTime = endTime;
		}
		
		public override string ToString()
		{
			return string.Format("Title={0}, Owner={1}, Group={2}, StartTime={3}, EndTime={4}, Duration={5} min", Title, Owner, Group, TimeUtils.convertUnixTimeToDateTime(StartTime), TimeUtils.convertUnixTimeToDateTime(EndTime), (EndTime-StartTime)/60);
		}
		
		public string[] ToStringArray()
		{
			return new string[] {Title, Owner, Group, TimeUtils.convertUnixTimeToDateTime(StartTime).ToString(), TimeUtils.convertUnixTimeToDateTime(EndTime).ToString(), Convert.ToString((EndTime-StartTime)/60)};
		}
		
		public object Clone()
		{
			Task cloneTask = new Task(this.Id, this.Title, this.Owner, this.Group, this.StartTime, this.EndTime);
			return cloneTask;
		}

	}
}
