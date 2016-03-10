/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 4/23/2015
 * Time: 15:16
 */
using System;
using System.ServiceModel;
using System.ServiceModel.Configuration;

namespace OSiSP_5.RSSExceptions
{
	/// <summary>
	/// Description of RSSException.
	/// </summary>
	public class RSSException : Exception
	{
		public RSSException(string message, Exception e) : base(message, e)
		{
		
		}
	}
}
