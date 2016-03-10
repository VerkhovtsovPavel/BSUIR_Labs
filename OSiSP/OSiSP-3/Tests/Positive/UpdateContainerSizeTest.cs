using System;
using NUnit.Framework;

namespace OSiSP_3.Tests.Positive
{
	[TestFixture]
	public class UpdateContainerSizeTest
	{
		private Container container;
		[Test]
		public void UpdateContainerSize()
		{
			container.AddElement(new object());
			container.AddElement("12");
			container.AddElement("First resize");
			container.AddElement(56.67);
			container.AddElement("Second resize");
			
			Assert.AreEqual(container.getCount(), 5);
		}
		
		[TestFixtureSetUp]
		public void Init()
		{
			container = new Container(2);
		}
	}
}
