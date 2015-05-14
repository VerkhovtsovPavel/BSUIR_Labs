﻿using System;

namespace Course_project.Utils
{
	public static class TimeUtils
	{
		public static DateTime convertUnixTimeToDateTime(int unixTime)
		{
			DateTime dtDateTime = timeZone(new DateTime(1970,1,1,0,0,0,0).ToUniversalTime());
			dtDateTime = dtDateTime.AddSeconds(unixTime);
    		return dtDateTime;
		}
		
		public static int DateTimeToUnixTime(DateTime dateTime)
		{
			return (int)((dateTime - new DateTime(1970, 1, 1)).TotalSeconds);
		}
		
		private static DateTime timeZone(DateTime dateTime){
			return TimeZoneInfo.ConvertTime(dateTime, Session.GetSession().TimeZone);
		}
	}
}
