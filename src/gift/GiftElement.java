package gift;

public abstract class GiftElement{
	
	public int cost;
	
	public abstract boolean equals(Object obj);
	public abstract String toString();
	public abstract int hashCode();
}
