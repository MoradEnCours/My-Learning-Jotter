// This import line isn't needed in JShell
import java.util.Arrays;

/**
 * Represents a monster in a monster battling game. A monster has a name,
 * one or two types, and up to four moves.
 * 
 * Model solution to JP2 Lab 3, 2020.
 * 
 * @author Mary Ellen Foster <MaryEllen.Foster@glasgow.ac.uk>
 */
public class Monster {
	
	/** The Monster's name */
	private String name;
	/** The Monster's types */
	private String[] types;
	/** The moves currently available to this Monster */
	private Move[] moves;

	/**
	 * Creates a new Monster with the given name and a single type.
	 * 
	 * @param name The name to use
	 * @param type The type to use
	 */
	public Monster(String name, String type) {
		this(name, new String[] { type });
	}
	
	/**
	 * Creates a new Monster with the given name and the two given types.
	 * 
	 * @param name The name to use
	 * @param type1 The first type to use
	 * @param type2 The second type to use
	 */
	public Monster(String name, String type1, String type2) {
		this(name, new String[] { type1, type2 });
	}
	
	/**
	 * Private constructor -- used to create a Monster with one or two types.
	 * 
	 * @param name The name to use
	 * @param types The types to use
	 */
	private Monster(String name, String[] types) {
		this.name = name;
		this.types = types;
		this.moves = new Move[4];
	}
	
	/**
	 * @return The monster's name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Checks whether the given type is one of the types of this monster.
	 * 
	 * @param type The type to check
	 * @return True if the given type is one of this monster's types, and false if not
	 */
	public boolean hasType (String type) {
		for (String t : types) {
			if (t.equals(type)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns the Move at the given position in this monster's list.
	 * 
	 * @param index The index to use (must be between 0 and 3)
	 * @return The Move at that position, or null if there is no such move
	 */
	public Move getMove(int index) {
		return moves[index];
	}
	
	/**
	 * Updates the Move at the given position in this monster's list.
	 * 
	 * @param index The index to use (must be between 0 and 3)
	 * @param move The move to store in that position
	 */
	public void setMove (int index, Move move) {
		moves[index] = move;
	}
	
	/**
	 * Returns a well formatted string representing this Monster.
	 */
	public String toString() {
		return name + " " + Arrays.toString(types);
	}

}
