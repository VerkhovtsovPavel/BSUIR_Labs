using System;

namespace OSiSP_3
{
	class Container
	{
		private object[] storage;
		private int currentCapacity;
		private int currentElementsCount;
		
		private readonly int  _initialCapacity;
		
		public void Reset(){
			lock(storage){
				currentElementsCount = 0;
				Array.Resize<object>(ref storage,_initialCapacity);
			}
		}
		
		public int getCount(){
			return currentElementsCount;
		}
		
		public Container(int initialCapacity){
			_initialCapacity = initialCapacity;
			currentCapacity = initialCapacity;
			storage = new object[initialCapacity];
		}
		
		
		
		public void AddElement(object obj){
			lock(storage){
				if(currentCapacity==currentElementsCount){
					UpdateStorageSize();
				}
					storage[currentElementsCount] = obj;
					currentElementsCount++;
			}
		}
		
		public bool AddElement(object obj, int number){
			if(number<0 || number>currentElementsCount){
				return false;
			}
			if(number==currentElementsCount){
				AddElement(obj);
			}
			
			lock(storage){
				if(currentCapacity==currentElementsCount){
					UpdateStorageSize();
				}
				for(int i = currentElementsCount+1; i > number+1; i--){
					storage[i]=storage[i-1];
				}
				storage[number] = obj;
			}
			return true;
		}
		
		public object ChangeElement(object obj, int number){
			object oldValue = null;
			lock(storage){
				oldValue = storage[number];
				storage[number] = obj;
			}
			
			return oldValue;
		}
		
		public bool DeleteElement(object obj){
			
			for(int i=0; i<currentElementsCount;i++){
				if(storage[i].Equals(obj))
				{
				   return DeleteElement(i);
				 }
			}
			return false;
		}
		
		public bool DeleteElement(int number){
			if(number>currentElementsCount || number<0){
				return false;
			}
			lock(storage){
				for(int i = number; i<currentElementsCount-1; i++){
					storage[i]=storage[i+1];
				}
			}
			currentElementsCount--;
			return true;
		}
		
		
		
		
		private void UpdateStorageSize(){
			lock(storage){
				Array.Resize<object>(ref storage,currentCapacity*2);
				currentCapacity*=2;
			}
		}
	}
	
}