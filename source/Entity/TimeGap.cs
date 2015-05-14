using System;

namespace Course_project.Entity
{
	public class TimeGap : IComparable
	{
		public int StartTime {get; set;}
		public int EndTime {get; set;}
		
		public TimeGap(int start, int end)
		{
			this.StartTime = start;
			this.EndTime = end;
		}
		
		public int getDuration(){
			return EndTime - StartTime;
		}


		public int CompareTo(object obj)
		{
			return this.StartTime;
		}

	}
}
