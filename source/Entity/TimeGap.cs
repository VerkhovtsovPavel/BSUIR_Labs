using System;

namespace Course_project.Entity
{
	public class TimeGap
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
	}
}
