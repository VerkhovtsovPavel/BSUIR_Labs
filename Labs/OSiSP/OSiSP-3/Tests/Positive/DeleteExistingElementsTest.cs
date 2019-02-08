using System;
using NUnit.Framework;

namespace OSiSP_3.Tests.Positive
{
	[TestFixture]
	public class DeleteExistingElementsTest
	{
		private Container container;
		private const string str = "12";
		
		[Test]
		public void DeleteByObject()
		{
			bool deleteResult = container.DeleteElement(str);
			Assert.True(deleteResult);
		}
		
		[Test]
		public void DeleteByNumber()
		{
			bool deleteResult = container.DeleteElement(2);
			Assert.True(deleteResult);
		}
		
		[TestFixtureSetUp]
		public void Init()
		{
			container = new Container(10);
			
			container.AddElement(new object());
			container.AddElement(str);
			container.AddElement(12);
			container.AddElement(56.67);
		}
	}
}
