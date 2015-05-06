/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 5/6/2015
 * Time: 21:43
 */
using System;

namespace Course_project.Utils
{
	/// <summary>
	/// Description of EnumUtils.
	/// </summary>
	public static class EnumUtils
	{
		public static T ToEnum<T>(this string value, bool ignoreCase = true)
    {
        return (T) Enum.Parse(typeof (T), value, ignoreCase);
    }
	}
}
