package enemies;

public class Zombie extends Enemy {
	public void talk() {
		System.out.println("Braaaaains ...");
	}
	
	public void eatBrains() {
		System.out.println("Yum");
	}
}
