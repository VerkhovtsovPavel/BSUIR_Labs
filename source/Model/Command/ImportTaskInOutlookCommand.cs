namespace Course_project.Model.Command
{
	using System;
	using System.Collections.Generic;
	using System.Runtime.InteropServices;
	using Microsoft.Office.Interop.Outlook;
	using Course_project.Entity;
	using Course_project.Exception;
	using Course_project.TaskDao;
	using Course_project.Utils;

	public class ImportTaskInOutlookCommand : ICommand
	{
		public object Execute(RequestParameters parameters)
		{
			List<Task> tasks = Dao.GetInstance().GetPrivateTasks(Session.GetSession().UserName);
			tasks.AddRange(Dao.GetInstance().GetSharedTasks(Session.GetSession().UserName));
		
			try
			{
				Application application = (Application)new Application();
				NameSpace mapiNS = application.GetNamespace("MAPI");

				mapiNS.Logon(string.Empty, null, null, null);

				AppointmentItem apt = null;
				foreach (Task task in tasks)
				{
					apt = (AppointmentItem)application.CreateItem(OlItemType.olAppointmentItem);
					apt.Subject = task.Title;
					apt.Body = task.Owner + " " + task.Id;

					apt.Start = TimeUtils.ConvertUnixTimeToDateTime(task.StartTime);
					apt.End = TimeUtils.ConvertUnixTimeToDateTime(task.EndTime);

					apt.ReminderMinutesBeforeStart = 15;        
					apt.BusyStatus = OlBusyStatus.olTentative; 

					apt.AllDayEvent = false;
					apt.Location = string.Empty;

					apt.Save();
				}
				
				return true;
			}
			catch (COMException)
			{
				throw new OutlookNotFoundException();
			}
		}
	}
}
