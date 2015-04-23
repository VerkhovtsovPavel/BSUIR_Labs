/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 4/23/2015
 * Time: 13:45
 */
using System;

using System.Windows.Forms;
using OSiSP_5.RSS;
using OSiSP_5.RSSExceptions;

namespace OSiSP_5.Forms
{

	public partial class MainView : Form
	{
		
		
		public MainView()
		{
			InitializeComponent();
		}


		void downloadButtonClick(object sender, EventArgs e)
		{			try {				MainRSS mainRSS = new MainRSS();				var atricles = mainRSS.getRSSArticles(url_txt.Text);
				TitlesView titles = new TitlesView(atricles);
				titles.Show();
			} catch (RSSException ex) {
				MessageBox.Show(ex.Message);
			}
		}
	}
}
