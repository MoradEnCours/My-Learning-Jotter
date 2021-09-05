import java.util.Arrays;

public class Main {

	/**
	 * A main method that can be used to test your implementation. Feel free to edit this
	 * method as much as you like -- you can add method calls, change parameters,
	 * whatever makes it easy to test your own code. You do not need to submit this
	 * class as part of the lab.
	 */
	public static void main(String[] args) {
		//System.out.println("?");
		// Here are some sample method calls -- try your own too!
		//System.out.println(TypedItem.getEffectiveness("Fire", "Water")); 

		//System.out.println(TypedItem.isValidType("Fire"));
		//System.out.println(TypedItem.isValidType("FFFFire"));
		
		//System.out.println(TypedItem.getEffectiveness("Fire", "Normal"));
		
		// These should all throw IllegalArgumentException
		//new Monster("AAA", "Fire", "Fire");
		//new Monster("BBB", "WWater");
		//new Monster("CCC", "Fire", "NNormal");
		//new Move("DDD", "GGGrass", 100);
		//new Move("EEE", "Grass", -10);
		//new Move("FFF", "Grass", 200);
		//new Monster("GGG", "Grass").setMove(5, null);
		//new Monster("HHH", "Electric").setMove(-1, null);

		// Here are some valid monsters and moves you can use to test things
		Monster heliolisk = new Monster ("Heliolisk", "Electric", "Normal");
		Monster ludicolo = new Monster ("Ludicolo", "Grass", "Water");
		Monster cinderace = new Monster ("Cinderace", "Fire");
		//Monster sinon = new Monster("Yaya", "Non");
		Monster sinonn = new Monster("Yaya", "Water", "Fire");
		//System.out.println(Arrays.toString(sinonn.getTypes()));
		

		Move thunderbolt = new Move("Thunderbolt", "Electric", 90);
		Move quickAttack = new Move("Quick Attack", "Normal", 40);
		Move hyperBeam = new Move("Hyper Beam", "Normal", 150);
		Move shi = new Move("Grrr", "Normal", 180);
		//System.out.println(shi.getPower());
		//System.out.println(quickAttack.hasType("Normal"));
		//System.out.println(Arrays.toString(quickAttack.getTypes()));
		
		
		//System.out.println(sinonn.getEffectivePower(shi, cinderace));
		//System.out.println(heliolisk.getEffectivePower(thunderbolt, ludicolo));
		
		heliolisk.setMove(0, thunderbolt);
		heliolisk.setMove(1, quickAttack);
		heliolisk.setMove(2, hyperBeam);
		
		
		//System.out.println(heliolisk.hasType("Electric"));
		//System.out.println(Arrays.toString(heliolisk.getTypes()));
		
		Move energyBall = new Move("Energy Ball", "Grass", 100);
		Move hydroPump = new Move("Hydro Pump", "Water", 110);
		Move firePunch = new Move("Fire Punch", "Fire", 75);
		Move megaPunch = new Move("Mega Punch", "Normal", 80);
		
		//System.out.println(ludicolo.getEffectivePower(firePunch, heliolisk));
		
		ludicolo.setMove(0, energyBall);
		ludicolo.setMove(1, hydroPump);
		ludicolo.setMove(2, firePunch);
		ludicolo.setMove(3, megaPunch);
		
		//System.out.println(ludicolo.getMove(3));
		System.out.println(ludicolo.chooseMove(heliolisk));
		//System.out.println(ludicolo.chooseMove(ludicolo));
		//System.out.println(heliolisk.chooseMove(heliolisk));
		//System.out.println(heliolisk.chooseMove(ludicolo));
		//System.out.println(sinonn.chooseMove(heliolisk));
		

		Move pyroBall = new Move("Pyro Ball", "Fire", 120);
		
		cinderace.setMove(0, pyroBall);
		
		// Some things to try with the monsters
		//System.out.println(heliolisk.chooseMove(cinderace));
		//System.out.println(cinderace.chooseMove(ludicolo));
		
		//System.out.println(ludicolo.getEffectivePower(firePump, cinderace));
		//System.out.println(ludicolo.getEffectivePower(hydroPump, cinderace));
	}

}
