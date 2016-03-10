/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 3/31/2015
 * Time: 22:40
 * 
 * To change this template use Tools | Options | Coding | Edit Standard Headers.
 */
using System;
using NUnit.Framework;

namespace OSiSP_3.Tests.Positive
{
	[TestFixture]
	public class PositiveAdd
	{
		private Container container;
		
		[Test]
		public void AddAtTheEnd()
		{
			int containerCount = container.getCount();
			container.AddElement(4);
			Assert.AreEqual(container.getCount(), containerCount+1);
		}
		
		[Test]
		public void AddAtPositionEnd()
		{
			int value = 23;
			container.AddElement(value, 3);
			Assert.AreEqual(container.getElement(3), value);
		}
		
		[TestFixtureSetUp]
		public void Init()
		{
			container = new Container(100);
			container.AddElement(1);
			container.AddElement(2);
			container.AddElement(3);
		}
	}
}
