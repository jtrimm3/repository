public abstract class Item {

	abstract public String name();
	abstract public int price();
	public int hashcode() {
		String a = name();
		return a.length();
	}
	public String toString() {
		return name() + " "	+ price();
	}

}