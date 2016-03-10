package entity;


public class Ship implements Comparable<Ship> {

	private final int capacity;
	private int load;
	private int counter;
	
	private static final int MAX_TRUING_PROCESS = 10;

	public Ship(int load, int capacity) {
		this.capacity = capacity;
		this.load = load;
	}

	public int getLoad() {
		return load;
	}

	public int getCapacity() {
		return capacity;
	}

	@Override
	public int compareTo(Ship o) {
		return -1;
	}
	
	public boolean addCounter(){
		if(counter==MAX_TRUING_PROCESS){
			return true;
		}
		counter++;
		return false;
	}
}
