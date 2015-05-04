/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 4/30/2015
 * Time: 20:29
 */
using System;

namespace Course_project.Utils
{
	/// <summary>
	/// Description of Class1.
	/// </summary>
	public static class TimeUtils
	{
		public static DateTime convertUnixTimeToDateTime(int unixTime)
		{
			DateTime dtDateTime = timeZone(new DateTime(1970,1,1,0,0,0,0, DateTimeKind.Utc));
			dtDateTime = dtDateTime.AddSeconds(unixTime);
    		return dtDateTime;
		}
		
		public static int DateTimeToUnixTime(DateTime dateTime)
		{
			return (int)(dateTime - new DateTime(1970, 1, 1).ToUniversalTime()).TotalSeconds;
		}
		
		private static DateTime timeZone(DateTime dateTime){
			return TimeZoneInfo.ConvertTime(dateTime, Session.getSession().TimeZone);
		}

	}
}
