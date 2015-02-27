package all;

public class Ship {
	private final int capacity;
	private int load;
	
	public Ship(int load,int capacity){
		this.capacity = capacity;
		this.load = load;
	}

	public int getLoad() {
		return load;
	}

	public int getCapacity() {
		return capacity;
	}
}
