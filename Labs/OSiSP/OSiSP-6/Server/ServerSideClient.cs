﻿namespace Server
{
	using System;
	
	public class ServerSideClient
	{
		public ServerSideClient(string userName, int age, string address, int port)
		{
			this.UserName = userName;
			this.Age = age;
			this.Address = address;
			this.Port = port;
			this.IsAlive = true;
		}
		
		public bool IsAlive {get; set;}
		
		public string UserName {get; set;}
		
		public int Age {get; set;}
		
		public string Address {get; set;}
		
		public int Port {get; set;}
	}
}
