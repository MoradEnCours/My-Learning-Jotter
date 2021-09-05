/**
 * Represents a single move in a monster battling game.
 * 
 * Model solution to JP2 Lab 3, 2020 remodified to solve JP2 Lab 4.
 * 
 * @author Morad (building on previous work of MEF)
 */

public class Move implements TypedItem {
	
	/** The move type */
	private String type;
	/** The move's name */
	private String name;
	/** The move's power */
	private int power;

	
	/**
	 * Creates a new Move with the given parameters.
	 * Throws an exception if type is not valid.
	 * Throws an exception if power is not between 0 and 180.
	 * 
	 * @param name The name to use
	 * @param type The type to use
	 * @param power The power to use
	 */
	public Move(String name, String type, int power) throws IllegalArgumentException
	{
		this.type = type;
		this.name = name;
		this.power = power;
		
		if (TypedItem.isValidType(type) == false)
		{
			throw new IllegalArgumentException("The given type is not valid.");
 		}
		if (power < 0 || power > 180)
		{
			throw new IllegalArgumentException("The value of power is not valid (must be between 0 and 180)");
		}
	}

	
	/**
	 * Checks whether the given type exists for the move.
	 * 
	 * @param type The type to check
	 * @return true if the given type is present,false otherwise.
	 */
	public boolean hasType(String type)
	{
		if (type == this.type)
		{
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * @return The type
	 */	
	public String[] getTypes()
	{
		String[] typeArray = {this.type};
		return typeArray;
	}
	
		
	/**
	 * @return The name
	 */
	public String getName() 
	{
		return this.name;
	}
	

	/**
	 * @return The power
	 */
	public int getPower() 
	{
		return this.power;
	}
	
	
	/**
	 * @return a well formatted string representing the Move.
	 */
	public String toString() 
	{
		return name + " (" + type + "): " + power;
	}

}
