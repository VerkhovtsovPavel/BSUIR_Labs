using System;
using NUnit.Framework;

namespace OSiSP_3.Tests.Negative
{
	[TestFixture]
	public class DeleteNonexistingElementsTest
	{
		private Container container;
				
		[Test]
		public void DeleteByObject()
		{
			bool deleteResult = container.DeleteElement(new object());
			Assert.False(deleteResult);
		}
		
		[Test]
		public void DeleteByNumber()
		{
			bool deleteResult = container.DeleteElement(10000);
			Assert.False(deleteResult);
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
