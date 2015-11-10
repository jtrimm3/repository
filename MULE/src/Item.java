public abstract class Item {

	public abstract  String name();
	public abstract int price();
	public final int hashcode() {
		String a = name();
		return a.length();
	}
	public final String toString() {
		return name() + " "	+ price();
	}

}