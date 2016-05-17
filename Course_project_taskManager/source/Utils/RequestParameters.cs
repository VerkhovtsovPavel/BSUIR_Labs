namespace Course_project.Utils
{
	using System;
	using System.Collections.Generic;

	public class RequestParameters
	{
		private readonly Dictionary<string, object> parameters;
		
		public RequestParameters()
		{
			this.parameters = new Dictionary<string, object>();
		}
	
		public void AddParameter<T>(string key, T value)
		{
			this.parameters.Add(key, value);	
		}
		
		public T GetParameter<T>(string key)
		{
			object value;
			this.parameters.TryGetValue(key, out value);
			return (T)Convert.ChangeType(value, typeof(T));
		}
	}
}
