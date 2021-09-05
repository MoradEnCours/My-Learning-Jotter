public class Animal {
    protected String name;
	
	public Animal(String name) {
		this.name = name;
	}

    public void move() {
        System.out.println(name + " can move");
    }
	
    public String getName() {
        return this.name;
    }
}
