
using System;
using NUnit.Framework;

namespace OSiSP_3.Tests.Negative
{
	[TestFixture]
	public class GetNonexistingElementTest
	{
		private Container container;
		
		[Test]
		public void GetOutOfRangeElement()
		{
			Assert.IsNull(container.getElement(300));
		}
		
		[Test]
		public void GetNegativeRangeElement()
		{
			Assert.IsNull(container.getElement(-30));
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
