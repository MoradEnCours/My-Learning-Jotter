public abstract class Animal {
    protected String name;
	
	public Animal(String name) {
		this.name = name;
	}

    public abstract void move();
	
    public String getName() {
        return this.name;
    }
	
	public String toString() {
		return "An animal named " + name;
	}
}
