namespace Course_project.Utils
{
	using System;
	using System.Security.Cryptography;
	
	public static class HashUtils
	{
		public static string MD5Hash(string input)
		{
			byte[] data = System.Text.Encoding.ASCII.GetBytes(input);
			data = MD5.Create().ComputeHash(data);
			return Convert.ToBase64String(data);
		}
	}
}
