public class Parrot extends FlyingBird implements TalkingCreature {
	public Parrot(String name) {
		super(name);
	}
	
	public void speak() {
		System.out.println("polly want a cracker");
	}
}