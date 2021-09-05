public interface TypedItem 
{
	/* Interface made use of in the refactoring process
	 * 
	 * Model solution to JP2 Lab 3, 2020 remodified to solve JP2 Lab 4.
	 * @author Morad (building on previous work of MEF)
	 */
	
	
	/*
	 * Checks whether the given type is one of the types of this monster.
	 * Interface is implemented in Classes Monster and Move
	 * 
	 * @param type The type to use
	 * @return true if type exists for monster, false otherwise
	 */
	boolean hasType(String type);
	
	
	/*
	 * return types in a string array
	 */
	String[] getTypes();
	
	
	/*
	 * Checks if parameter value matches any of the given options
	 * 
	 * @param type The type being checked for validity.
	 * @return true if given type matches a type, false otherwise
	 */
	
	static boolean isValidType(String type)
	{
		if (type == "Normal"   || 
			type == "Fire"     || 
			type == "Water"    ||
			type == "Electric" ||
			type == "Grass")
			return true;
		else
			return false;
	}
	
	
	/*
	 * Compares attack type and defending monster's type(s)
	 * Comparisons are made to calculate the final effectiveness based on table in specification's
	 * 
	 * @param attackType The type of attack to be delivered
	 * @param defendType The type(s) of the defending monster
	 * 
	 * @return double value of 0.5 if effectiveness is very little
	 * @return double value of 1.0 if effectiveness is normal
	 * @return double value of 2.0 if effectiveness is super
	 * @return 1.0 if none of the above apply - This just returns the original value (no change);
	 *  ... added because an else statement was required if all cases happened to fail
	 */
	
	static double getEffectiveness(String attackType, String defendType)
	{
		if (attackType == "Normal")
			return 1.0;
		
		if (defendType == "Normal")
			return 1.0;
		
		if (attackType != "Electric" && defendType == "Electric")
			return 1.0;
		
		
		if (attackType == "Fire" && defendType == "Water")
			return 0.5;
		if (attackType == "Water" && defendType == "Fire")
			return 2.0;
		
		if (attackType == "Electric" && defendType == "Fire")
			return 1.0;
		
		if (attackType == "Grass" && defendType == "Fire")
			return 0.5;
		if (attackType == "Fire" && defendType == "Grass")
			return 2.0;
		
		if (attackType == "Electric" && defendType == "Water")
			return 2.0;
		
		if (attackType == "Grass" && defendType == "Water")
			return 2.0;
		if (attackType == "Water" && defendType == "Grass")
			return 0.5;
		
		if (attackType == "Electric" && defendType == "Grass")
			return 0.5;
		
		
		if (attackType == "Fire" && defendType == "Fire"         ||
			attackType == "Water" && defendType == "Water"       ||
			attackType == "Electric" && defendType == "Electric" ||
			attackType == "Grass" && defendType == "Grass")
			return 0.5;
		
		else
			return 1.0;
				
	}
		
}
	