/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 04/17/2015
 * Time: 20:37
 * 
 * To change this template use Tools | Options | Coding | Edit Standard Headers.
 */
namespace Course_project
{
	partial class RegistrationView
	{
		/// <summary>
		/// Designer variable used to keep track of non-visual components.
		/// </summary>
		private System.ComponentModel.IContainer components = null;
		private System.Windows.Forms.Button submit_button;
		private System.Windows.Forms.Label login_label;
		private System.Windows.Forms.Label password_label;
		private System.Windows.Forms.Label label3;
		private System.Windows.Forms.Label first_name_label;
		private System.Windows.Forms.Label last_name_label;
		private System.Windows.Forms.TextBox last_name_textBox;
		private System.Windows.Forms.ComboBox timeZone_comboBox;
		private System.Windows.Forms.TextBox first_name_textBox;
		private System.Windows.Forms.TextBox login_textBox;
		private System.Windows.Forms.TextBox password_textBox;
		
		/// <summary>
		/// Disposes resources used by the form.
		/// </summary>
		/// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
		protected override void Dispose(bool disposing)
		{
			if (disposing) {
				if (components != null) {
					components.Dispose();
				}
			}
			base.Dispose(disposing);
		}
		
		/// <summary>
		/// This method is required for Windows Forms designer support.
		/// Do not change the method contents inside the source code editor. The Forms designer might
		/// not be able to load this method if it was changed manually.
		/// </summary>
		private void InitializeComponent()
		{
			this.submit_button = new System.Windows.Forms.Button();
			this.login_label = new System.Windows.Forms.Label();
			this.password_label = new System.Windows.Forms.Label();
			this.label3 = new System.Windows.Forms.Label();
			this.login_textBox = new System.Windows.Forms.TextBox();
			this.password_textBox = new System.Windows.Forms.TextBox();
			this.timeZone_comboBox = new System.Windows.Forms.ComboBox();
			this.first_name_label = new System.Windows.Forms.Label();
			this.last_name_label = new System.Windows.Forms.Label();
			this.first_name_textBox = new System.Windows.Forms.TextBox();
			this.last_name_textBox = new System.Windows.Forms.TextBox();
			this.SuspendLayout();
			// 
			// submit_button
			// 
			this.submit_button.Location = new System.Drawing.Point(166, 160);
			this.submit_button.Name = "submit_button";
			this.submit_button.Size = new System.Drawing.Size(75, 23);
			this.submit_button.TabIndex = 0;
			this.submit_button.Text = "Submit";
			this.submit_button.UseVisualStyleBackColor = true;
			this.submit_button.Click += new System.EventHandler(this.Submit_buttonClick);
			// 
			// login_label
			// 
			this.login_label.Location = new System.Drawing.Point(21, 25);
			this.login_label.Name = "login_label";
			this.login_label.Size = new System.Drawing.Size(172, 23);
			this.login_label.TabIndex = 1;
			this.login_label.Text = "Login";
			// 
			// password_label
			// 
			this.password_label.Location = new System.Drawing.Point(21, 48);
			this.password_label.Name = "password_label";
			this.password_label.Size = new System.Drawing.Size(100, 23);
			this.password_label.TabIndex = 1;
			this.password_label.Text = "Password";
			// 
			// label3
			// 
			this.label3.Location = new System.Drawing.Point(21, 123);
			this.label3.Name = "label3";
			this.label3.Size = new System.Drawing.Size(100, 23);
			this.label3.TabIndex = 1;
			this.label3.Text = "TimeZone";
			// 
			// login_textBox
			// 
			this.login_textBox.Location = new System.Drawing.Point(99, 22);
			this.login_textBox.Name = "login_textBox";
			this.login_textBox.Size = new System.Drawing.Size(261, 20);
			this.login_textBox.TabIndex = 2;
			// 
			// password_textBox
			// 
			this.password_textBox.Location = new System.Drawing.Point(99, 45);
			this.password_textBox.Name = "password_textBox";
			this.password_textBox.PasswordChar = '*';
			this.password_textBox.Size = new System.Drawing.Size(261, 20);
			this.password_textBox.TabIndex = 2;
			this.password_textBox.UseSystemPasswordChar = true;
			// 
			// timeZone_comboBox
			// 
			this.timeZone_comboBox.FormattingEnabled = true;
			this.timeZone_comboBox.Items.AddRange(new object[] {
			"Afghanistan (UTC+04:30)",
			"Albania (UTC+01:00)",
			"Algeria (UTC+01:00)",
			"Andorra (UTC+01:00)",
			"Angola (UTC+01:00)",
			"Antigua and Barbuda (UTC−04:00)",
			"Argentina (UTC−03:00)",
			"Armenia (UTC+04:00)",
			"Australia (UTC+05:00)",
			"Australia (UTC+06:30)",
			"Australia (UTC+07:00)",
			"Australia (UTC+08:00)",
			"Australia (UTC+09:30)",
			"Australia (UTC+10:00)",
			"Australia (UTC+10:30)",
			"Australia (UTC+11:30)",
			"Austria (UTC+01:00)",
			"Azerbaijan (UTC+04:00)",
			"Bahamas (UTC−05:00)",
			"Bahrain (UTC+03:00)",
			"Bangladesh (UTC+06:00)",
			"Barbados (UTC−04:00)",
			"Belarus (UTC+03:00)",
			"Belgium (UTC+01:00)",
			"Belize (UTC−06:00)",
			"Benin (UTC+01:00)",
			"Bhutan (UTC+06:00)",
			"Bolivia (UTC−04:00)",
			"Bosnia and Herzegovina (UTC+01:00)",
			"Botswana (UTC+02:00)",
			"Brazil (UTC−02:00)",
			"Brazil (UTC−03:00)",
			"Brazil (UTC−04:00)",
			"Brazil (UTC−05:00)",
			"Brunei (UTC+08:00)",
			"Bulgaria (UTC+02:00)",
			"Burkina Faso (UTC+00:00)",
			"Burundi (UTC+02:00)",
			"Cambodia (UTC+07:00)",
			"Cameroon (UTC+01:00)",
			"Canada (UTC−03:30",
			"Canada (UTC−04:00)",
			"Canada (UTC−05:00)",
			"Canada (UTC−06:00)",
			"Canada (UTC−07:00)",
			"Canada (UTC−08:00)",
			"Cape Verde (UTC−01:00)",
			"Central African Republic (UTC+01:00)",
			"Chad (UTC+01:00)",
			"Chile (UTC−03:00)",
			"Chile (UTC−05:00)",
			"China (UTC+08:00)",
			"Colombia (UTC−05:00)",
			"Comoros (UTC+03:00)",
			"Costa Rica (UTC−06:00)",
			"Croatia (UTC+01:00)",
			"Cuba (UTC−05:00)",
			"Cyprus (UTC+02:00)",
			"Czech Republic (UTC+01:00)",
			"Côte d\'Ivoire (UTC+00:00)",
			"Democratic Republic of the Congo (UTC+01:00)",
			"Democratic Republic of the Congo (UTC+02:00)",
			"Djibouti (UTC+03:00)",
			"Dominica (UTC−04:00)",
			"Dominican Republic (UTC−04:00)",
			"East Timor (UTC+09:00)",
			"Ecuador (UTC−05:00)",
			"Ecuador (UTC−06:00)",
			"Egypt (UTC+02:00)",
			"El Salvador (UTC−06:00)",
			"Equatorial Guinea (UTC+01:00)",
			"Eritrea (UTC+03:00)",
			"Estonia (UTC+02:00)",
			"Ethiopia (UTC+03:00)",
			"Federated States of Micronesia (UTC+10:00)",
			"Federated States of Micronesia (UTC+11:00)",
			"Fiji (UTC+12:00)",
			"Finland (UTC+02:00)",
			"France (UTC+01:00)",
			"France (UTC+03:00)",
			"France (UTC+04:00)",
			"France (UTC+05:00)",
			"France (UTC+11:00)",
			"France (UTC+12:00)",
			"France (UTC−03:00)",
			"France (UTC−04:00)",
			"France (UTC−08:00)",
			"France (UTC−09:00)",
			"France (UTC−09:30",
			"France (UTC−10:00)",
			"Gabon (UTC+01:00)",
			"Gambia (UTC+00:00)",
			"Georgia (UTC+04:00)",
			"Germany (UTC+01:00)",
			"Ghana (UTC+00:00)",
			"Greece (UTC+02:00)",
			"Grenada (UTC−04:00)",
			"Guatemala (UTC−06:00)",
			"Guinea (UTC+00:00)",
			"Guinea-Bissau (UTC+00:00)",
			"Guyana (UTC−04:00)",
			"Haiti (UTC−05:00)",
			"Honduras (UTC−06:00)",
			"Hong Kong (UTC+08:00)",
			"Hungary (UTC+01:00)",
			"Iceland (UTC+00:00)",
			"India (UTC+05:30)",
			"Indonesia (UTC+07:00)",
			"Indonesia (UTC+08:00)",
			"Indonesia (UTC+09:00)",
			"Iran (UTC+03:30)",
			"Iraq (UTC+03:00)",
			"Ireland (UTC+00:00)",
			"Israel (UTC+02:00)",
			"Italy (UTC+01:00)",
			"Jamaica (UTC−05:00)",
			"Japan (UTC+09:00)",
			"Jordan (UTC+02:00)",
			"Kazakhstan (UTC+05:00)",
			"Kazakhstan (UTC+06:00)",
			"Kenya (UTC+03:00)",
			"Kingdom of Denmark (UTC+00:00)",
			"Kingdom of Denmark (UTC+01:00)",
			"Kingdom of Denmark (UTC−01:00)",
			"Kingdom of Denmark (UTC−03:00)",
			"Kingdom of Denmark (UTC−04:00)",
			"Kingdom of the Netherlands (UTC+01:00)",
			"Kingdom of the Netherlands (UTC−04:00)",
			"Kiribati (UTC+12:00)",
			"Kiribati (UTC+13:00)",
			"Kiribati (UTC+14:00)",
			"Korea, North (UTC+09:00)",
			"Korea, South (UTC+09:00)",
			"Kosovo (UTC+01:00)",
			"Kuwait (UTC+03:00)",
			"Kyrgyzstan (UTC+06:00)",
			"Laos (UTC+07:00)",
			"Latvia (UTC+02:00)",
			"Lebanon (UTC+02:00)",
			"Lesotho (UTC+02:00)",
			"Liberia (UTC+00:00)",
			"Libya (UTC+02:00)",
			"Liechtenstein (UTC+01:00)",
			"Lithuania (UTC+02:00)",
			"Luxembourg (UTC+01:00)",
			"Macedonia (UTC+01:00)",
			"Madagascar (UTC+03:00)",
			"Malawi (UTC+02:00)",
			"Malaysia (UTC+08:00)",
			"Maldives (UTC+05:00)",
			"Mali (UTC+00:00)",
			"Malta (UTC+01:00)",
			"Marshall Islands (UTC+12:00)",
			"Mauritania (UTC+00:00)",
			"Mauritius (UTC+04:00)",
			"Mexico (UTC−05:00)",
			"Mexico (UTC−06:00)",
			"Mexico (UTC−07:00)",
			"Mexico (UTC−08:00)",
			"Moldova (UTC+02:00)",
			"Monaco (UTC+01:00)",
			"Mongolia (UTC+07:00)",
			"Mongolia (UTC+08:00)",
			"Montenegro (UTC+01:00)",
			"Morocco (UTC+00:00)",
			"Mozambique (UTC+02:00)",
			"Myanmar (UTC+06:30",
			"Namibia (UTC+01:00)",
			"Nauru (UTC+12:00)",
			"Nepal (UTC+05:45)",
			"New Zealand (UTC+12:00)",
			"New Zealand (UTC+12:45",
			"New Zealand (UTC+13:00)",
			"New Zealand (UTC−10:00)",
			"New Zealand (UTC−11:00)",
			"Nicaragua (UTC−06:00)",
			"Niger (UTC+01:00)",
			"Nigeria (UTC+01:00)",
			"Norway (UTC+01:00)",
			"Oman (UTC+04:00)",
			"Pakistan (UTC+05:00)",
			"Palau (UTC+09:00)",
			"Panama (UTC−05:00)",
			"Papua New Guinea (UTC+10:00)",
			"Paraguay (UTC−04:00)",
			"Peru (UTC−05:00)",
			"Philippines (UTC+08:00)",
			"Poland (UTC+01:00)",
			"Portugal (UTC+00:00)",
			"Portugal (UTC−01:00)",
			"Qatar (UTC+03:00)",
			"Republic of the Congo (UTC+01:00)",
			"Romania (UTC+02:00)",
			"Russia (UTC+02:00)",
			"Russia (UTC+03:00)",
			"Russia (UTC+04:00)",
			"Russia (UTC+05:00)",
			"Russia (UTC+07:00)",
			"Russia (UTC+08:00)",
			"Russia (UTC+09:00)",
			"Russia (UTC+10:00)",
			"Russia (UTC+11:00)",
			"Russia (UTC+12:00)",
			"Russia(UTC+06:00)",
			"Rwanda (UTC+02:00)",
			"Saint Kitts and Nevis (UTC−04:00)",
			"Saint Lucia (UTC−04:00)",
			"Saint Vincent and the Grenadines (UTC−04:00)",
			"Samoa (UTC+13:00)",
			"San Marino (UTC+01:00)",
			"Saudi Arabia (UTC+03:00)",
			"Senegal (UTC+00:00)",
			"Serbia (UTC+01:00)",
			"Seychelles (UTC+04:00)",
			"Sierra Leone (UTC+00:00)",
			"Singapore (UTC+08:00)",
			"Slovakia (UTC+01:00)",
			"Slovenia (UTC+01:00)",
			"Solomon Islands (UTC+11:00)",
			"Somalia (UTC+03:00)",
			"South Africa (UTC+02:00)",
			"South Africa (UTC+03:00)",
			"South Sudan (UTC+03:00)",
			"Spain (UTC+00:00)",
			"Spain (UTC+01:00)",
			"Sri Lanka (UTC+05:30)",
			"Sudan (UTC+03:00)",
			"Suriname (UTC−03:00)",
			"Swaziland (UTC+02:00)",
			"Sweden (UTC+01:00)",
			"Switzerland (UTC+01:00)",
			"Syria (UTC+02:00)",
			"São Tomé and Príncipe (UTC+00:00)",
			"Taiwan (UTC+08:00)",
			"Tajikistan (UTC+05:00)",
			"Tanzania (UTC+03:00)",
			"Thailand (UTC+07:00)",
			"Togo (UTC+00:00)",
			"Tonga (UTC+13:00)",
			"Trinidad and Tobago (UTC−04:00)",
			"Tunisia (UTC+01:00)",
			"Turkey (UTC+02:00)",
			"Turkmenistan (UTC+05:00)",
			"Tuvalu (UTC+12:00)",
			"Uganda (UTC+03:00)",
			"Ukraine (UTC+02:00)",
			"United Arab Emirates (UTC+04:00)",
			"United Kingdom (UTC+00:00)",
			"United Kingdom (UTC+01:00)",
			"United Kingdom (UTC+02:00)",
			"United Kingdom (UTC+06:00)",
			"United Kingdom (UTC−02:00)",
			"United Kingdom (UTC−03:00)",
			"United Kingdom (UTC−04:00)",
			"United Kingdom (UTC−05:00)",
			"United Kingdom (UTC−08:00)",
			"United States (UTC+10:00)",
			"United States (UTC+12:00)",
			"United States (UTC−04:00)",
			"United States (UTC−05:00)",
			"United States (UTC−06:00)",
			"United States (UTC−07:00)",
			"United States (UTC−08:00)",
			"United States (UTC−09:00)",
			"United States (UTC−10:00)",
			"United States (UTC−11:00)",
			"United States (UTC−12:00)",
			"Uruguay (UTC−03:00)",
			"Uzbekistan (UTC+05:00)",
			"Vanuatu (UTC+11:00)",
			"Vatican City (UTC+01:00)",
			"Venezuela (UTC−04:30)",
			"Vietnam (UTC+07:00)",
			"Yemen (UTC+03:00)",
			"Zambia (UTC+02:00)",
			"Zimbabwe (UTC+02:00)"});
			this.timeZone_comboBox.Location = new System.Drawing.Point(99, 120);
			this.timeZone_comboBox.Name = "timeZone_comboBox";
			this.timeZone_comboBox.Size = new System.Drawing.Size(261, 21);
			this.timeZone_comboBox.TabIndex = 3;
			// 
			// first_name_label
			// 
			this.first_name_label.Location = new System.Drawing.Point(21, 71);
			this.first_name_label.Name = "first_name_label";
			this.first_name_label.Size = new System.Drawing.Size(100, 23);
			this.first_name_label.TabIndex = 1;
			this.first_name_label.Text = "First Name";
			// 
			// last_name_label
			// 
			this.last_name_label.Location = new System.Drawing.Point(21, 97);
			this.last_name_label.Name = "last_name_label";
			this.last_name_label.Size = new System.Drawing.Size(100, 23);
			this.last_name_label.TabIndex = 1;
			this.last_name_label.Text = "Last Name";
			// 
			// first_name_textBox
			// 
			this.first_name_textBox.Location = new System.Drawing.Point(99, 68);
			this.first_name_textBox.Name = "first_name_textBox";
			this.first_name_textBox.Size = new System.Drawing.Size(261, 20);
			this.first_name_textBox.TabIndex = 2;
			// 
			// last_name_textBox
			// 
			this.last_name_textBox.Location = new System.Drawing.Point(99, 94);
			this.last_name_textBox.Name = "last_name_textBox";
			this.last_name_textBox.Size = new System.Drawing.Size(261, 20);
			this.last_name_textBox.TabIndex = 2;
			// 
			// RegistrationView
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(405, 210);
			this.Controls.Add(this.timeZone_comboBox);
			this.Controls.Add(this.last_name_textBox);
			this.Controls.Add(this.first_name_textBox);
			this.Controls.Add(this.password_textBox);
			this.Controls.Add(this.login_textBox);
			this.Controls.Add(this.label3);
			this.Controls.Add(this.last_name_label);
			this.Controls.Add(this.first_name_label);
			this.Controls.Add(this.password_label);
			this.Controls.Add(this.login_label);
			this.Controls.Add(this.submit_button);
			this.Name = "RegistrationView";
			this.Text = "Registration";
			this.ResumeLayout(false);
			this.PerformLayout();

		}
	}
}
