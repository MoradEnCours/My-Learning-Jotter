public class Dog extends Animal {
    private String breed;

	public Dog(String name, String breed) {
		super(name);
		this.breed = breed;
	}
	
    public void move() {
        System.out.println(name + " can walk and run");
    }

    public void bark() {
        System.out.println("woof");
    }
	
	public String toString() {
		return "a " + breed + " named " + name;
	}
}

