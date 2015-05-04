/*
 * Created by SharpDevelop.
 * User: VerkhovtsovPavel
 * Date: 04.05.2015
 * Time: 16:42
 */
using System;
using System.Security.Cryptography;

namespace Course_project.Utils
{
	/// <summary>
	/// Description of HashUtils.
	/// </summary>
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
