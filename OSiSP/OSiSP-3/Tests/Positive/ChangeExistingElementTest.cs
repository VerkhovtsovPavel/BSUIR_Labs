using System;
using NUnit.Framework;

namespace OSiSP_3.Tests.Positive
{
	[TestFixture]
	public class ChangeExistingElementTestcs
	{
		private Container container;
				
		[Test]
		public void ChangeElement()
		{
			container.ChangeElement(32,2);
			Assert.AreEqual(container.getElement(2), 32);
		}
		
		
		
		[TestFixtureSetUp]
		public void Init()
		{
			container = new Container(10);
			
			container.AddElement(new object());
			container.AddElement("12");
			container.AddElement(12);
			container.AddElement(56.67);
		}
	}
}
