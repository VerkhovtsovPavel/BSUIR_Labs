/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 4/23/2015
 * Time: 15:45
 */
using System;
using System.Drawing;
using System.Windows.Forms;

namespace OSiSP_5.Forms
{
	/// <summary>
	/// Description of Titles.
	/// </summary>
	public partial class Titles : Form
	{
		Items[] items;
		public Titles(Items[] items)
		{
			this.items = items;
			InitializeComponent();
			setTitle("Title"); //Change to channel title
			fillListBox();
		}

		void fillListBox()
		{
			foreach (Items item in items) {
				titles_lb.Items.Add(item.title);
			}
		}
		void Titles_lbSelectedIndexChanged(object sender, EventArgs e)
		{
			int selectedNote = ((ListBox)sender).SelectedIndex;
			Note note = new Note(items[selectedNote]);
			note.Show();
		}
	}
}
