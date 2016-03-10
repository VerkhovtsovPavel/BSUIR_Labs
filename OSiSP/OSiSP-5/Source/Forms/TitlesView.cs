/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 4/23/2015
 * Time: 15:45
 */
using System;
using System.Drawing;
using System.Windows.Forms;
using OSiSP_5.RSS;

namespace OSiSP_5.Forms
{
	/// <summary>
	/// Description of Titles.
	/// </summary>
	public partial class TitlesView : Form
	{
		Article[] items;
		public TitlesView(Article[] items, int maxTitleLength)
		{
			this.items = items;
			InitializeComponent();
			setTitle("RSS Atricle Titles");
			setSize(maxTitleLength);
			fillListBox();
		}

		void fillListBox()
		{
			foreach (Article item in items) {
				titles_lb.Items.Add(item.title);
			}
		}
		void Titles_lbSelectedIndexChanged(object sender, EventArgs e)
		{
			int selectedNote = ((ListBox)sender).SelectedIndex;
			ArticleView note = new ArticleView(items[selectedNote]);
			note.Show();
		}

		void setSize(int maxTitleLength)
		{
			this.Width = maxTitleLength * 6 + 30;
			this.MinimumSize = new Size(this.Width, this.Height);
			this.MaximumSize = new Size(this.Width, this.Height);
			this.titles_lb.Width = maxTitleLength * 6;
		}
	}
}
