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
			System.DateTime dtDateTime = new DateTime(1970,1,1,0,0,0,0,System.DateTimeKind.Utc);
			dtDateTime = dtDateTime.AddSeconds( unixTime ).ToLocalTime;
			//TODO Add user time zone
    		return dtDateTime;
		}
		
		public static double DateTimeToUnixTime(DateTime dateTime)
		{
    		return (dateTime - new DateTime(1970, 1, 1).ToLocalTime()).TotalSeconds;
		}
		
		private static void timeZome(DateTime dateTime){
			dateTime = TimeZoneInfo.ConvertTime(dateTime, Session.getSession().TimeZone);
		}

	}
}
