/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 4/23/2015
 * Time: 14:23
 */
using System;

namespace OSiSP_5.Forms
{
	/// <summary>
	/// Description of ImageOfChannel.
	/// </summary>
public class ImageOfChanel
{
    //Заголовок изображения.
    public string imgTitle;
 
    //Ссылка на изображение.
    public string imgLink;
 
    //URL адрес сообщения.
    public string imgURL;
 
    public ImageOfChanel()
    {
        imgTitle = "";
        imgLink = "";
        imgURL = "";
    }
}
}
