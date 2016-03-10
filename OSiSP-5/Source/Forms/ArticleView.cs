/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 4/23/2015
 * Time: 16:10
 */
using System;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using OSiSP_5.RSS;

namespace OSiSP_5.Forms
{
	/// <summary>
	/// Description of Note.
	/// </summary>
	public partial class ArticleView : Form
	{
		public ArticleView(Article item)
		{
			InitializeComponent();		
			note_text.Text = Regex.Replace(item.description, "<[^>]*>", String.Empty).Trim();
		}
	}
}
