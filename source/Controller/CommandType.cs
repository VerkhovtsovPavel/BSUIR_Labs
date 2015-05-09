/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 5/6/2015
 * Time: 21:20
 */
namespace Course_project.Controller
{
	using System;
	public enum CommandType
	{
		ADD_PRIVATE_TASK,
		UPDATE_GROUPS,
		ADD_SHARE_TASK,
		LOGIN,
		REGISTRATION,
		GET_TASKS_FROM_RANGE,
		IMPORT_TO_OUTLOOK,
		ADD_FLEXIBLE_TASK_IN_STORE,
		GET_USER_GROUPS,
		GET_TIME_SPENT_BY_GROUP
	}
}