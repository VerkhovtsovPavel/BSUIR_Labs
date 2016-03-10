using System;
using NUnit.Framework;

namespace OSiSP_3.Tests.Negative
{
	[TestFixture]
	public class ChangeNonexistingElementTestcs
	{
		private Container container;
				
		[Test]
		public void ChangeElement()
		{
			object changeResult = container.ChangeElement(32,23);
			Assert.IsNull(changeResult);
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
