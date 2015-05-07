/*
 * Created by SharpDevelop.
 * User: VerkhovtsovPavel
 * Date: 04.05.2015
 * Time: 21:32
 * 
 * To change this template use Tools | Options | Coding | Edit Standard Headers.
 */
using System;
using System.Collections.Generic;
using Course_project.Entity;

namespace Course_project.Utils
{
	/// <summary>
	/// Description of RequestParameters.
	/// </summary>
	public class RequestParameters
	{
		private Dictionary<String, Object> parameters;
		
		public RequestParameters()
		{
			this.parameters = new Dictionary<String, Object>();
		}
		
		public void addInt(string key, int value){
			parameters.Add(key, value);	
		}
		
		public void addString(string key, string value){
			parameters.Add(key, value);	
		}
		
		public void addDateTime(string key, DateTime value){
			parameters.Add(key, value);	
		}

		public void addTask(string key, Task value)
		{
			parameters.Add(key, value);	
		}
		
		public int getInt(string key){
			object value;
			parameters.TryGetValue(key, out value);
			
			return Convert.ToInt32(value);
		}
		
		public string getString(string key){
			object value;
			parameters.TryGetValue(key, out value);
			
			return Convert.ToString(value);
		}
		
		public DateTime getDateTime(string key){
			object value;
			parameters.TryGetValue(key, out value);
			
			return Convert.ToDateTime(value);
		}
	}
}
