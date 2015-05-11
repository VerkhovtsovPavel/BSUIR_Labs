using System;
using System.Collections.Generic;
using System.Runtime.InteropServices;
using Microsoft.Office.Interop.Outlook;
using Course_project.Entity;
using Course_project.Exception;
using Course_project.TaskDao;
using Course_project.Utils;

namespace Course_project.Model.Command
{
	public class ImportTaskInOutlookCommand : ICommand
	{

	public object Execute(RequestParameters parameters)
	{
		List<Task> tasks = Dao.getInstance().getPrivateTasks(Session.GetSession().UserName);
		tasks.AddRange(Dao.getInstance().getSharedTasks(Session.GetSession().UserName));
		
		try {
				Application olApp = (Application)new Application();
				NameSpace mapiNS = olApp.GetNamespace("MAPI");

				mapiNS.Logon("", null, null, null);

				AppointmentItem apt = null;
				foreach (Task task in tasks) {
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
				return true;
			} catch (COMException) {
				throw new OutlookNotFoundException();
			}
	}


	}
}
