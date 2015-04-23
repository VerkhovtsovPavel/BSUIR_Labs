/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 4/23/2015
 * Time: 14:20
 */
using System;

namespace OSiSP_5.Forms
{
	/// <summary>
	/// Description of ChannelClass.
	/// </summary>
	public class ChannelClass
	{
		//Заголовок сайта - источника.
		public string title;
 
		//Описание сайта - источника.
		public string description;
 
		//Ссылка на сайт источника.
		public string link;
 
		// копирайт.
		public string copyright;
 
		public ChannelClass()
		{           
			title = "";
			description = "";
			link = "";
			copyright = "";
		}
	}
}
