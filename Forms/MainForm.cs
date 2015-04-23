/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 4/23/2015
 * Time: 13:45
 */
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Windows.Forms;
using System.Xml;
using System.IO;
using System.Net;
using OSiSP_5.RSS;
using OSiSP_5.RSSExceptions;

namespace OSiSP_5.Forms
{

	public partial class MainForm : Form
	{
		
		
		public MainForm()
		{
			InitializeComponent();
		}


		void downloadButtonClick(object sender, EventArgs e)
		{			try {				MainRSS mainRSS = new MainRSS();				var items = mainRSS.getNewMessage(url_txt.Text);
				//webBrowser1.Navigate(Environment.CurrentDirectory + "\\Message.html");
				Titles titles = new Titles(items);
				titles.Show();
			} catch (RSSException ex) {
				MessageBox.Show(ex.Message);
			}
		}
	}
}
