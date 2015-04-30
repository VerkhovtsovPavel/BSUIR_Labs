using System;
using System.Collections.Generic;
using Microsoft.Office.Interop.Outlook;
using Course_project.Entity;
using Course_project.Utils;

namespace Course_project.Model
{

	public class OutlookImporter
	{
		public OutlookImporter()
		{
		}
		
		public static void importTasks(List<Task> tasks)
		{
			Application olApp = (Application)new Application();
			NameSpace mapiNS = olApp.GetNamespace("MAPI");

			mapiNS.Logon("", null, null, null);

			AppointmentItem apt = null;
			foreach(Task task in tasks){
			
			apt = (AppointmentItem)olApp.CreateItem(OlItemType.olAppointmentItem);
			apt.Subject = task.Title;
			apt.Body = task.Owner + " " + task.Id;

			apt.Start = TimeUtils.convertUnixTimeToDateTime(task.StartTime);
			apt.End = TimeUtils.convertUnixTimeToDateTime(task.EndTime);

			apt.ReminderMinutesBeforeStart = 15;        
			apt.BusyStatus = OlBusyStatus.olTentative; 

			apt.AllDayEvent = false;
			apt.Location = "";

			apt.Save();
			}
		}
	}
}
