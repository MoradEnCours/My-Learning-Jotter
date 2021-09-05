public class FlyingBird extends Bird {
	public FlyingBird(String name) {
		super(name);
	}
	
	public void move() {
		System.out.println(name + " can fly");
	}

}