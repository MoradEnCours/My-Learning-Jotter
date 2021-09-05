package rpsls;

import java.util.Objects;

/**
 * Represents a single player in the RPSLS game.
 * 
 * Sample solution to JP2 Lab Exam 2019 (version F).
 * 
 * @author mefoster
 */
public class Player {

	/** The player's name */
	private String name;
	/** The player's score */
	private int score;
	
	/**
	 * Creates a new Player with the given name and a score of zero.
	 * 
	 * @param name The Player's name
	 */
	public Player(String name) {
		this.name = name;
		this.score = 0;
	}

	/**
	 * Chooses a random symbol to play.
	 * 
	 * @return A randomly chosen symbol
	 */
	public Symbol chooseSymbol() {
		return Symbol.chooseRandomSymbol();
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, score);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return Objects.equals(name, other.name) && score == other.score;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", score=" + score + "]";
	}
	
}
