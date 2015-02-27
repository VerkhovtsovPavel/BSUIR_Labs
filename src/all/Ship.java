package all;


public class Ship implements Comparable<Ship> {

	private final int capacity;
	private int load;

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
		return 0;
	}
}
