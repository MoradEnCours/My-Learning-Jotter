package enemies;

public abstract class Enemy {
	public abstract void talk();
	
	public static void main(String[] args) {
		Enemy[] enemies = { new Werewolf(), new Zombie(), new Werewolf() };
		for (Enemy e2 : enemies) {
			e2.talk();
		}
	}
}
