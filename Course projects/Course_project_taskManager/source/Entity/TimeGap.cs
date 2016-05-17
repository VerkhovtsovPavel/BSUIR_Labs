namespace Course_project.Entity
{
	using System;
	
	public class TimeGap : IComparable
	{
		public TimeGap(int start, int end)
		{
			this.StartTime = start;
			this.EndTime = end;
		}
		
		public int StartTime {get; set;}

		public int EndTime {get; set;}
		
		public int GetDuration()
		{
			return this.EndTime - this.StartTime;
		}

		public int CompareTo(object obj)
		{
			return this.StartTime;
		}
	}
}
