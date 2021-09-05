package rpsls;

import java.util.Random;

/**
 * The possible symbols that a player can choose in RPSLS.
 * 
 * Sample solution to JP2 Lab Exam 2019 (version F).
 * 
 * @author mefoster
 */
public enum Symbol {
	ROCK, PAPER, SCISSORS, LIZARD, SPOCK;
	
	private static final Random RAND = new Random();

	/**
	 * Helper method to choose a random symbol, for players to choose. (A version of this
	 * method could also appear in the Player class.)
	 * 
	 * @return A randomly chosen Symbol
	 */
	public static Symbol chooseRandomSymbol() {
		return values()[RAND.nextInt(values().length)];
	}

}
