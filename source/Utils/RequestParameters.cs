using System;
using System.Collections.Generic;

namespace Course_project.Utils
{
	public class RequestParameters
	{
		private readonly Dictionary<String, Object> parameters;
		
		public RequestParameters()
		{
			this.parameters = new Dictionary<String, Object>();
		}
	
		public void AddParameter<T>(string key, T value)
		{
			parameters.Add(key, value);	
		}
		
		public T GetParameter<T>(string key)
		{
			object value;
			parameters.TryGetValue(key, out value);
			return (T)Convert.ChangeType(value, typeof(T));
		}
	}
}
