package hide_seek;

import java.util.Set;

/**
 * A hide-and-seek game, including a list of players and a method to run the game.
 * 
 * Sample solution to JP2 Lab Exam 2019 (version B).
 * 
 * @author mefoster
 */
public class HideAndSeek {
	
	/** The players in the game. */
	private Set<Player> players;

	/**
	 * Creates a new hide-and-seek game including the given players. The list of players
	 * must have at least one seeker.
	 * 
	 * @param players The players of the game
	 * @throws IllegalArgumentException If there is no seeker
	 */
	public HideAndSeek(Set<Player> players) {
		boolean hasSeeker = false;
		for (Player p : players) {
			if (p.isSeeker()) {
				hasSeeker = true;
				break;
			}
		}
		if (!hasSeeker) {
			throw new IllegalArgumentException("Game must have at least one seeker");
		}
		this.players = players;
	}
	
	/**
	 * Returns the players in the game.
	 * 
	 * @return The players
	 */
	public Set<Player> getPlayers() {
		return players;
	}
	
	/**
	 * Plays the hide-and-seek game for the given number of rounds. Exits early if all of the hiders
	 * have been found.
	 * 
	 * @param numRounds How many rounds to play
	 */
	public void playGame(int numRounds) {
		for (int i = 0; i < numRounds; i++) {
			for (Player p : players) {
				if (p.isActive()) {
					if (p.isSeeker()) {
						// Find the other players in the same location and catch them
						for (Player p2 : players) {
							if (p2 != p && p.isActive() && !p.isSeeker() && p.getLocation() == p2.getLocation()) {
								p.setActive(false);
							}
						}
					}
					// The player moves to a new location
					p.setLocation(Location.chooseRandomLocation());
				}
			}
			// Check if there is at least one active non-seeker, and break if not
			boolean activeHider = false;
			for (Player p : players) {
				if (p.isActive() && !p.isSeeker()) {
					activeHider = true;
					break;
				}
			}
			if (!activeHider) {
				break;
			}
		}
	}
}
