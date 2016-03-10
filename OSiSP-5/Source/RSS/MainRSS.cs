/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 4/23/2015
 * Time: 14:11
 */
using System;
using System.Xml;
using System.Net;
using OSiSP_5.RSSExceptions;

namespace OSiSP_5.RSS
{
	
	/// <summary>
	/// Description of MainRSS.
	/// </summary>
	public class MainRSS
	{
		Article[] articles;

		int maxTitleLength;
		public Article[] getRSSArticles(string url)
		{

			try {
				WebRequest wr = WebRequest.Create(url);
				wr.Proxy.Credentials = CredentialCache.DefaultCredentials;
				XmlTextReader xtr = new XmlTextReader(wr.GetResponse().GetResponseStream());
				XmlDocument doc = new XmlDocument();
				doc.Load(xtr);
				XmlNode root = doc.DocumentElement;
				articles = new Article[root.SelectNodes("//rss/channel/item").Count];
				XmlNodeList nodeList;
				nodeList = root.ChildNodes;
 				int count = 0;

				foreach (XmlNode chanel in nodeList) {                  
					foreach (XmlNode chanel_item in chanel) {
						if (chanel_item.Name == "item") {
							XmlNodeList itemsList = chanel_item.ChildNodes;
							articles[count] = new Article();
							foreach (XmlNode item in itemsList) {
								if (item.Name == "title") {
									articles[count].title = String.Format("{0}) {1}",(count+1),item.InnerText);
									if(articles[count].title.Length>maxTitleLength){
										maxTitleLength = articles[count].title.Length;
									}
								}
								if (item.Name == "description") {
									articles[count].description = item.InnerText;
								}
							}
							count += 1;
						}
					}
				}
				return articles;
			} catch (Exception exc) {
				throw new RSSException("Ошибка получения данных", exc);
			}
		}

		public int MaxTitleLength {
			get {
				return maxTitleLength;
			}
		}
	}
}
