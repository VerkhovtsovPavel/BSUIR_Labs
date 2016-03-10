using System;
using System.Threading;

namespace OSiSP_3
{
	class Container
	{
		private object[] storage;
		private int currentCapacity;
		private int currentElementsCount;
		
		private readonly int _initialCapacity;
		
		public void Reset()
		{
			Interlocked.Exchange(ref currentElementsCount, 0);
			lock (storage) {
				Array.Resize<object>(ref storage, _initialCapacity);
			}
		}
		
		public object getElement(int number)
		{
			try {
				if (number < 0 || number > currentElementsCount - 1) {
					return null;
				}
				return storage[number];
			} catch (Exception) {
				return null;
			}
		}
		
		public int getCount()
		{
			return currentElementsCount;
		}
		
		public Container(int initialCapacity)
		{
			_initialCapacity = initialCapacity;
			currentCapacity = initialCapacity;
			storage = new object[initialCapacity];
		}
		
		
		
		public void AddElement(object obj)
		{
			lock (storage) {
				if (currentCapacity == currentElementsCount) {
					UpdateStorageSize();
				}
				storage[currentElementsCount] = obj;
			}	
			Interlocked.Increment(ref currentElementsCount);
			
		}
		
		public bool AddElement(object obj, int number)
		{
			try {
				if (number < 0 || number > currentElementsCount) {
					return false;
				}
				if (number == currentElementsCount) {
					AddElement(obj);
				}
			
				lock (storage) {
					if (currentCapacity == currentElementsCount) {
						UpdateStorageSize();
					}
					for (int i = currentElementsCount + 1; i > number + 1; i--) {
						storage[i] = storage[i - 1];
					}
					storage[number] = obj;
				}
				Interlocked.Increment(ref currentElementsCount);
				return true;
			} catch (Exception) {
				return false;
			}
		}
		
		public object ChangeElement(object obj, int number)
		{
			try {
				object oldValue = null;
				if (number >= 0 && number < currentElementsCount) {
					lock (storage) {
						oldValue = storage[number];
						storage[number] = obj;
					}
				}
			
				return oldValue;
			} catch (Exception) {
				return null;
			}
		}
		
		public bool DeleteElement(object obj)
		{
			try {
				for (int i = 0; i < currentElementsCount; i++) {
					if (storage[i].Equals(obj)) {
						return DeleteElement(i);
					}
				}
				return false;
			} catch (Exception) {
				return false;
			}
		}
		
		public bool DeleteElement(int number)
		{
			try {
				if (number > currentElementsCount || number < 0) {
					return false;
				}
				lock (storage) {
					for (int i = number; i < currentElementsCount - 1; i++) {
						storage[i] = storage[i + 1];
					}
				}
				Interlocked.Decrement(ref currentElementsCount);
				return true;
			} catch (Exception) {
				return false;
			}
		}
		
		
		
		
		private void UpdateStorageSize()
		{
			lock (storage) {
				Array.Resize<object>(ref storage, currentCapacity * 2);
				currentCapacity *= 2;
			}
		}
	}
	
}