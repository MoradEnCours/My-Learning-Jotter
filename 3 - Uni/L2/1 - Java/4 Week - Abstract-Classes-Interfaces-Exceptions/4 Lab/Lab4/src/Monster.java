import java.util.Arrays;

/**
 * Represents a monster in a monster battling game. A monster has a name,
 * one or two types, and up to four moves. In this version, an interface is implemented
 * 
 * Model solution to JP2 Lab 3, 2020 remodified to solve JP2 Lab 4.
 * 
 * @author Morad (building on previous work of MEF)
 */
public class Monster implements TypedItem
{
	
	/** The Monster's name */
	private String name;
	/** The Monster's types */
	private String[] types;
	/** The moves currently available to this Monster */
	private Move[] moves;
	

	/**
	 * Creates a new Monster with the given name and a single type.
	 * Throws an exception if the given type is not valid (based on the interface method .isValidType)
	 * This avoids monsters being given unsuitable types but still being accepted anyway
	 * 
	 * @param name The name to use
	 * @param type The type to use
	 */
	
	public Monster(String name, String type) throws IllegalArgumentException
	{
		this(name, new String[] { type });
		if (TypedItem.isValidType(type) == false)
			{
				throw new IllegalArgumentException("The given type is not valid.");
		 	}	
	}
	
		
	/**
	 * Creates a new Monster with the given name and the two given types.
	 * Throws an exception if either given type is not valid; also, when both types are the same.
	 * 
	 * @param name The name to use
	 * @param type1 The first type to use
	 * @param type2 The second type to use
	 */
	
	public Monster(String name, String type1, String type2) throws IllegalArgumentException
	{
		this(name, new String[] { type1, type2 });
		
		if (TypedItem.isValidType(type1) == false || TypedItem.isValidType(type2) == false)
		{
			throw new IllegalArgumentException("At least one given type is not valid.");
		}
		
		if (type1.equals(type2))
			throw new IllegalArgumentException("Both types are the same.");
	}
	
	
	
	/**
	 * Private constructor -- used to create a Monster with one or two types.
	 * 
	 * @param name The name to use
	 * @param types The types to use
	 */
	
	private Monster(String name, String[] types) 
	{
		this.name = name;
		this.types = types;
		this.moves = new Move[4];
	}
	
	
	/**
	 * @return The monster's name
	 */
	public String getName() 
	{
		return this.name;
	}
	
	
	/**
	 * Checks whether the given type is one of the types of this monster.
	 * 
	 * @param type The type to check
	 * @return true if the given type is one of this monster's types, false if not
	 */
		
	public boolean hasType(String type)
	{
		for (String t : types) 
		{
			if (t.equals(type)) 
			{
				return true;
			}
		}
		return false;
	}
	
		
	/*
	 * @return the monster's type(s)
	 */
	
	public String[] getTypes()
	{
		return this.types;		
	}
	
		
	/**
	 * Returns the Move at the given position in the monster's list.
	 * Throws an exception if the given index is out of the specified range.
	 * 
	 * @param index The index to use (must be between 0 and 3)
	 * @return The Move at that position, or null if there is no such move
	 */
	
	public Move getMove(int index) throws IllegalArgumentException
	{		
		if (index < 0 || index > 3)
			throw new IllegalArgumentException("The given index is not between 0 and 3.");
	
		return moves[index];
	}
	
	
	/**
	 * Updates the Move at the given position in this monster's list.
	 * Again, throws an exception if the the given index is not within the range specified.
	 * 
	 * @param index The index to use (must be between 0 and 3)
	 * @param move The move to store in that position
	 */
	public void setMove (int index, Move move) throws IllegalArgumentException
	{
		if (index < 0 || index > 3)
			throw new IllegalArgumentException("The given index is not between 0 and 3.");
		
		moves[index] = move;
	}
	
	
	/**
	 * Returns a well formatted string representing the Monster.
	 */
	public String toString() 
	{
		return name + " " + Arrays.toString(types);
	}
	
	
	/*
	 * Calculates a double value which modifies strength of base attacks,
	 * ... which depends on type of move used against the defending monster's type(s).
	 * The method considers monster with either one type or two and calculates appropriately.
	 * Additionally, if the attacking monster has type same as that of the move, a bonus is applied.
	 * 
	 * @param move The move to be used in the attack.
	 * @param defender The monster in the defending position against the move.
	 * @return the effective power of the given attack against the particular defending monster.
	 */
	
	public double getEffectivePower(Move move, Monster defender)
	{
		int basePower = move.getPower();
		String attackType = move.getTypes()[0];		
		String defendType1 = defender.getTypes()[0];
		
		double effect1 = TypedItem.getEffectiveness(attackType, defendType1);		
		
		if (defender.getTypes().length == 2)
		{
			String defendType2 = defender.getTypes()[1];
			double effect2 = TypedItem.getEffectiveness(attackType, defendType2);
			effect1 *= effect2;			// This part only runs if defending monster has 2 types 
		}
		
		double bonus = 1.5;
		if (this.types.length == 1)
		{
			if (attackType ==  this.types[0])
			{
				effect1 *= bonus;
			}
		}
		else
			if (attackType ==  this.types[0] || attackType == this.types[1])
			{
				effect1 *= bonus;
			}
		
		// Reminder that effect1 may have been multiplied by effect2 for 2-type monster.
		double effectivePower = basePower * effect1;
		
		return effectivePower;
			
	}
	
	
	/*
	 * Selects the best move for the attacking monster to use against the defending monster.
	 * Sets the first move in array as the best move, then for-loops and compares...
	 * ... replacing bestMove whenever a stronger move is available.
	 * Skips null values and returns null if monster has null for all indexes in array (no moves).
	 * 
	 * @param defender The defending monster to decide which move to use against.
	 * @return the move with the greatest impact against the defending monster.
	 */
	
	public Move chooseMove(Monster defender)
	{	
		Move bestMove = this.moves[0];
		for (int i=0; i < this.moves.length -1; i++)
		{
			if (this.moves[i] == null)
			{
				continue;
			}
				
			if ((getEffectivePower(this.moves[i], defender) >
			     getEffectivePower(bestMove, defender)))
				bestMove = this.moves[i];
		}
		
		if (bestMove == null)
			return null;
		
	
		return bestMove;
	}
	
}
