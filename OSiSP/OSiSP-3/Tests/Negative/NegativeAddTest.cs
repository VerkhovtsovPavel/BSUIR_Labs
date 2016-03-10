/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 3/31/2015
 * Time: 22:36
 * 
 * To change this template use Tools | Options | Coding | Edit Standard Headers.
 */
using System;
using NUnit.Framework;

namespace OSiSP_3.Tests.Negative
{

	[TestFixture]
	public class NegativeAddTest
	{
		private Container container;
		
		[Test]
		public void NegativeRangeAdd()
		{
			Assert.IsFalse(container.AddElement("str", -12));
		}
		
		[Test]
		public void OutOfRangeAdd()
		{
			Assert.IsFalse(container.AddElement("str", 15));
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
