public abstract class Bird extends Animal {
	public Bird (String name) {
		super(name);
	}
	
	public String toString() {
		return "a bird named " + name;
	}
}