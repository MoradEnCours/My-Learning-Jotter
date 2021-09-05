import java.util.Arrays;

/**
 * Represents a monster in a monster battling game. A monster has a name,
 * one or two types, and up to four moves.
 * 
 * Model solution to JP2 Lab 4, 2020.
 * 
 * @author Mary Ellen Foster <MaryEllen.Foster@glasgow.ac.uk>
 */
public class Monster implements TypedItem {
	
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
	public Monster(String name, String type) throws IllegalArgumentException {
		this(name, new String[] { type });
	}
	
	/**
	 * Creates a new Monster with the given name and the two given types.
	 * 
	 * @param name The name to use
	 * @param type1 The first type to use
	 * @param type2 The second type to use
	 */
	public Monster(String name, String type1, String type2) throws IllegalArgumentException {
		this(name, new String[] { type1, type2 });
	}
	
	/**
	 * Private constructor -- used to create a Monster with one or two types.
	 * 
	 * @param name The name to use
	 * @param types The types to use
	 */
	private Monster(String name, String[] types) throws IllegalArgumentException {
		this.name = name;
		if (types.length == 2 && types[0].equals(types[1])) {
			throw new IllegalArgumentException("Duplicate type");
		}
		for (String type : types) {
			if (!TypedItem.isValidType(type)) {
				throw new IllegalArgumentException("Invalid type: " + type);
			}
		}
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
	@Override
	public boolean hasType (String type) {
		for (String t : types) {
			if (t.equals(type)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the type(s) of this Monster.
	 * 
	 * @return The type(s)
	 */
	@Override
	public String[] getTypes() {
		return types;
	}

	
	/**
	 * Returns the Move at the given position in this monster's list.
	 * 
	 * @param index The index to use (must be between 0 and 3)
	 * @return The Move at that position, or null if there is no such move
	 */
	public Move getMove(int index) {
		try {
			return moves[index];
		} catch (ArrayIndexOutOfBoundsException ex) {
			throw new IllegalArgumentException("Index out of range: " + index);
		}
	}
	
	/**
	 * Updates the Move at the given position in this monster's list.
	 * 
	 * @param index The index to use (must be between 0 and 3)
	 * @param move The move to store in that position
	 */
	public void setMove (int index, Move move) {
		try {
			moves[index] = move;
		} catch (ArrayIndexOutOfBoundsException ex) {
			throw new IllegalArgumentException("Index out of range: " + index);
		}
	}
	
	/**
	 * Returns a well formatted string representing this Monster.
	 */
	public String toString() {
		return name + " " + Arrays.toString(types);
	}
	
	/**
	 * Computes the effective power of the given Move when used against the given Monster,
	 * incorporating type effectiveness computations.
	 * 
	 * @param move The move to consider
	 * @param defender The defending Monster
	 * @return The effective power
	 * @see TypedItem#getEffectiveness(String, String)
	 */
	public double getEffectivePower (Move move, Monster defender) {
		String moveType = move.getTypes()[0];
		
		// Incorporate effectiveness from all defender types
		double effectiveness = 1.0;
		for (String type : defender.getTypes()) {
			effectiveness *= TypedItem.getEffectiveness(moveType, type);
		}

		// Check for STAB
		if (this.hasType(moveType)) {
			effectiveness *= 1.5;
		}
		
		return effectiveness * move.getPower();
	}
	
	/**
	 * Chooses the most effective Move to use against the given defender.
	 * 
	 * @param defender The defending Monster
	 * @return The most effective Move to use, or null if this Monster has no Moves
	 */
	public Move chooseMove (Monster defender) {
		// Default values
		Move bestMove = null;
		double bestPower = 0;

		for (Move move : moves) {
			if (move != null) {
				double power = getEffectivePower(move, defender);
				if (bestMove == null || power > bestPower) {
					bestMove = move;
					bestPower = power;
				}
			}
		}

		return bestMove;
	}

}
