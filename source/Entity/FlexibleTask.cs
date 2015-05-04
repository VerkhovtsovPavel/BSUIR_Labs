﻿/*
 * Created by SharpDevelop.
 * User: VerkhovtsovPavel
 * Date: 03.05.2015
 * Time: 17:36
 */
using System;
using System.Collections.Generic;

namespace Course_project.Entity
{
	/// <summary>
	/// Description of FlexibleTask.
	/// </summary>
	public class FlexibleTask : Task
	{
		public int MaxParts {get; set;}
		public int MinTimeOfOnePart {get; set;}
		
		public List<Task> DependedTasks {get; set;}
	}
}
