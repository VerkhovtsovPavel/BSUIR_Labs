/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 4/23/2015
 * Time: 14:11
 */
using System;
using System.Xml;
using System.IO;
using System.Net;
using OSiSP_5.Forms;
using OSiSP_5.RSSExceptions;

namespace OSiSP_5.RSS
{
	
	/// <summary>
	/// Description of MainRSS.
	/// </summary>
	public class MainRSS
	{
		
		//Массив элементов item
		Items[] articles;
 
		//Объект класса ChannelClass
		ChannelClass channel = new ChannelClass();

		//Объект класса рисунка
		ImageOfChanel imageChanel = new ImageOfChanel();

		public MainRSS()
		{
		}
		
		public Items[] getNewMessage(string url)
		{

			try {
				//Для предотвращения ошибки 407 (Удаленный сервер возвратил ошибку:
				//(407) Требуется проверка подлинности посредника)
				//в сетях с прокси сервером
				//загрузка RSS ленты осуществляется через класс WebRequest
				//с указанием настроек прокси.
				WebRequest wr = WebRequest.Create(url);
 
				//Указываем системные учетные данные приложения,
				//передаваемые прокси-серверу для выполнения проверки подлинности.
				wr.Proxy.Credentials = CredentialCache.DefaultCredentials;
 
				//Инициализируем класс XmlTextReader, который
				//обеспечивает прямой доступ (только для чтения) к потокам данных XML.
				//и передаем ему экземпляр класса System.IO.Stream(GetResponseStream)
				//для чтения данных из интернет-ресурса.
				XmlTextReader xtr = new XmlTextReader(wr.GetResponse().GetResponseStream());
 
				//Инициализируем класс "XmlDocument". Который представляет XML-документ
				//и включает в себя метод "Load",
				//предназначенный для загрузки документа
				//с помощью объекта "XMLReader".
				XmlDocument doc = new XmlDocument();
				doc.Load(xtr);
 
				//XmlNode root - содержит корневой элемент XML для
				//загруженного элемента.
				XmlNode root = doc.DocumentElement;
 
				//Получаем количество элементов item в RSS-потоке,
				//используя SelectNodes() и
				//выражение XPath, которое позволяет это сделать.
				articles = new Items[root.SelectNodes("//rss/channel/item").Count];
 
				//Инициализируем класс System.Xml.XmlNodeList,
				//содержащий все дочерние узлы данного потока (channel).
				XmlNodeList nodeList;
				nodeList = root.ChildNodes;
 
				//Индикатор числового типа,
				//для массива articles[].
				int count = 0;
 
				//Цикл для прохода по всем каналам в RSS-потоке.
				foreach (XmlNode chanel in nodeList) {
					//Цикл для прохода по всем элементам cnannel.                   
					foreach (XmlNode chanel_item in chanel) {
						//Название канала RSS-потока.
						if (chanel_item.Name == "title") {
							channel.title = chanel_item.InnerText;
						}
						//Описание канала RSS-потока.
						if (chanel_item.Name == "description") {
							channel.description = chanel_item.InnerText;
						}
						//----
						if (chanel_item.Name == "copyright") {
							channel.copyright = chanel_item.InnerText;
						}
						//Ссылка на сайт RSS-потока.
						if (chanel_item.Name == "link") {
							channel.link = chanel_item.InnerText;
						}
						//Получение изображения канала RSS-потока.
						if (chanel_item.Name == "img") {
							XmlNodeList imgList = chanel_item.ChildNodes;
							foreach (XmlNode img_item in imgList) {
								if (img_item.Name == "url") {
									imageChanel.imgURL = img_item.InnerText;
								}
								if (img_item.Name == "link") {
									imageChanel.imgLink = img_item.InnerText;
								}
								if (img_item.Name == "title") {
									imageChanel.imgTitle = img_item.InnerText;
								}
							}
						}
						//Обработка сообщения канала RSS-потока.
						if (chanel_item.Name == "item") {
							XmlNodeList itemsList = chanel_item.ChildNodes;
							articles[count] = new Items();
 
							foreach (XmlNode item in itemsList) {
								//Заголовок сообщения.
								if (item.Name == "title") {
									articles[count].title = item.InnerText;
								}
								//Ссылка на сообщение в интернете.
								if (item.Name == "link") {
									articles[count].link = item.InnerText;
								}
								//Описание сообщения, по сути оно и является
								//самим сообщением в формате HTML.
								if (item.Name == "description") {
									articles[count].description = item.InnerText;
								}
								//Дата публикации сообщения.
								if (item.Name == "pubDate") {
									articles[count].pubDate = item.InnerText;
								}
							}
							//Увеличение счетчика сообщений
							//для массива articles.
							count += 1;
						}
					}
				}
				//После выполнения этого метода,
				//объекты классов, будут заполнены данными.
				//В imageChanel содержатся все данные о рисунке (если он есть),
				//В channel - все параметры канала,
				//Массив articles - будет содержать все сообщения. 
				return articles;
			} catch (Exception exc) {
				throw new RSSException("Ошибка получения данных", exc);
			}
		}
	}
}
