using System;
using NUnit.Framework;

namespace OSiSP_3.Tests.Positive
{
	[TestFixture]
	public class CreateContainerTest
	{
		private Container container;
		private Random random;
		private const string abc = "abcdefghijklmnopqrstuvwxyz";
		
		[Test]
		public void FillContainerInteger()
		{
			container.Reset();
			
			for(int i=0; i<10; i++){
				container.AddElement(random.Next());
			}
			
			Assert.AreEqual(container.getCount(), 10);
		}
		
		[Test]
		public void FillContainerDouble()
		{
			container.Reset();
			
			for(int i=0; i<10; i++){
				container.AddElement(random.NextDouble());
			}
			
			Assert.AreEqual(container.getCount(), 10); 
		}
		
		
		
		[Test]
		public void FillContainerString()
		{
			container.Reset();
			
			for(int i=0; i<10; i++){
				string str = "";
				for(int j=0; j < random.Next(20, 100); j++){
					str+=abc[random.Next()%abc.Length];
				}
				container.AddElement(str);
			}
			
			Assert.AreEqual(container.getCount(), 10); 
		}
		
		[TestFixtureSetUp]
		public void Init()
		{
			container = new Container(100);
			random = new Random();
		}
	}
}

