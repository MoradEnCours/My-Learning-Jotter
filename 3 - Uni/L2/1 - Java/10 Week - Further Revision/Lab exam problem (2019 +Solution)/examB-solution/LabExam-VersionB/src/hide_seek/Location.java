package hide_seek;

import java.util.Random;

/**
 * The possible locations where a player can hide in the hide-and-seek game.
 * 
 * Sample solution to JP2 Lab Exam 2019 (version B).
 * 
 * @author mefoster
 */
public enum Location {
	BEDROOM, ATTIC, LIVING_ROOM, DINING_ROOM, BATHROOM, BASEMENT;

	private static final Random RAND = new Random();

	/**
	 * Helper method to choose a random location, for players to move. (A version of this
	 * method could also appear in the Player class.)
	 * 
	 * @return A randomly chosen Location
	 */
	public static Location chooseRandomLocation() {
		return values()[RAND.nextInt(values().length)];
	}
}
