package data;

public class Treasure {
	public String name;
	public long cost;

	
	public Treasure(String name, long cost){
		this.name = name;
		this.cost = cost;
	}
	
	@Override
	public String toString(){
		return this.name+"  "+this.cost;
	}
}
