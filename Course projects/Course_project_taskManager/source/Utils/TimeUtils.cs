namespace Course_project.Utils
{
	using System;
	
	public static class TimeUtils
	{
		public static DateTime ConvertUnixTimeToDateTime(int unixTime)
		{
			DateTime dateTime = ConvertToUserTimeZone(new DateTime(1970,1,1,0,0,0,0).ToUniversalTime());
			dateTime = dateTime.AddSeconds(unixTime);
    		return dateTime;
		}
		
		public static int DateTimeToUnixTime(DateTime dateTime)
		{
			return (int)(dateTime - new DateTime(1970, 1, 1)).TotalSeconds;
		}
		
		private static DateTime ConvertToUserTimeZone(DateTime dateTime)
		{
			return TimeZoneInfo.ConvertTime(dateTime, Session.GetSession().TimeZone);
		}
	}
}
