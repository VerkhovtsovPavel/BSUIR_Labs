using System;
using NUnit.Framework;

namespace OSiSP_3.Tests.Positive
{
	[TestFixture]
	public class GetExistingElementTest
	{
		private Container container;
		
		[Test]
		public void GetElement()
		{
			Assert.AreEqual(container.getElement(1), "12");
		}
		
		[TestFixtureSetUp]
		public void Init()
		{
			container = new Container(100);
			
			container.AddElement(new object());
			container.AddElement("12");
			container.AddElement(12);
			container.AddElement(56.67);
		}
	}
}