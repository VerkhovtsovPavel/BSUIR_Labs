package gift;

public class Sweet extends GiftElement {

	public int sweetness;

	public Sweet(int sweethess) {
		this.sweetness = sweethess;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Sweet)) {
			return false;
		}

		Sweet sweet = (Sweet) obj;
		if (this.sweetness == sweet.sweetness) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public String toString() {
		return "Sweet with sweetness " + this.sweetness;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + sweetness;
		return result;
	}
}
