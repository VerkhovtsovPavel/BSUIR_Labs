/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 4/23/2015
 * Time: 14:18
 */
using System;

namespace OSiSP_5.Forms
{
	/// <summary>
	/// Description of Item.
	/// </summary>
	public class Items
{
    //Заголовок сообщения.
    public string title;
    //Ссылка на страницу сообщения в интернете.
    public string link;
    //Краткий обзор сообщения.
    public string description;
    //Дата публикации сообщения.
    public string pubDate;
 
    public Items()
    {
        title = "";
        link = "";
        description = "";
        pubDate = "";
    }
}
}
