package subjects;

public class Subject {
	private int size;
	private int price;
	
	public Subject(int size, int price){
		this.setPrice(price);
		this.setSize(size);
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
