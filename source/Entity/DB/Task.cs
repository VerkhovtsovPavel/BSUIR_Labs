namespace Course_project.Entity
{
	using System;
	using MongoDB.Bson;
	using Course_project.Utils;
	
	[Serializable]
	public class Task : ICloneable, IComparable
	{
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
		
		private Task(ObjectId id, string title, string owner, string group, int startTime, int endTime) : 	this(title, owner, group, startTime, endTime)
		{
			this.Id = id;
		}
		
		public ObjectId Id { get; protected set; }

		public string Title { get; set; }
		
		public string Owner { get; set; }
		
		public string Group { get; set; }
		
		public int StartTime { get; set; }
		
		public int EndTime { get; set; }
		
		public override string ToString()
		{
			return string.Format("Title={0}, Owner={1}, Group={2}, StartTime={3}, EndTime={4}, Duration={5} min", this.Title, this.Owner, this.Group, TimeUtils.ConvertUnixTimeToDateTime(this.StartTime), TimeUtils.ConvertUnixTimeToDateTime(this.EndTime), (this.EndTime-this.StartTime)/60);
		}
		
		public string[] ToStringArray()
		{
			return new string[] {this.Title, this.Owner, this.Group, TimeUtils.ConvertUnixTimeToDateTime(this.StartTime).ToString(), TimeUtils.ConvertUnixTimeToDateTime(this.EndTime).ToString(), Convert.ToString((this.EndTime-this.StartTime)/60)};
		}
		
		public virtual object Clone()
		{
			Task cloneTask = new Task(this.Id, this.Title, this.Owner, this.Group, this.StartTime, this.EndTime);
			return cloneTask;
		}

		public virtual int CompareTo(object obj)
		{
			return this.StartTime;
		}
	}
}
