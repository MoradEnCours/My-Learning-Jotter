/**
 * Represents a single move in a monster battling game.
 * 
 * Model solution to JP2 Lab 3, 2020.
 * 
 * @author Mary Ellen Foster <MaryEllen.Foster@glasgow.ac.uk>
 */

public class Move {
	
	/** The move type */
	private String type;
	/** The move's name */
	private String name;
	/** The move's power */
	private int power;
	
	/**
	 * Creates a new Move with the given parameters
	 * @param name The name to use
	 * @param type The type to use
	 * @param power The power to use
	 */
	public Move(String name, String type, int power) {
		this.type = type;
		this.name = name;
		this.power = power;
	}

	/**
	 * @return The type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return The name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return The power
	 */
	public int getPower() {
		return power;
	}
	
	/**
	 * Returns a well formatted string representing this Move.
	 */
	public String toString() {
		return name + " (" + type + "): " + power;
	}

}
