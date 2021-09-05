package monster;

/**
 * Represents a type for monsters and moves. Also provides a method to check
 * the effectiveness of one type against another.
 * 
 * @author mefos
 */
public enum Type {
	NORMAL, FIRE, WATER, ELECTRIC, GRASS;
	
	public static final double SUPER_EFFECTIVE = 2.0;
	public static final double EFFECTIVE = 1.0;
	public static final double NOT_EFFECTIVE = 0.5;

	/**
	 * Checks the effectiveness of the current type against the given type.
	 * 
	 * @param defendType The type to defend against
	 * @return The effectiveness, as a multiplier
	 */
	// (Eclipse doesn't like the switch below but it really does work)
	@SuppressWarnings("incomplete-switch")
	public double getEffectiveness (Type defendType) {
		double effectiveness = EFFECTIVE;
		
		switch (this) {
		case FIRE:
		 	if (defendType == Type.FIRE || defendType == Type.WATER) {
		 		effectiveness = NOT_EFFECTIVE;
			} else if (defendType == Type.GRASS) {
				effectiveness = SUPER_EFFECTIVE;
			}
		 	break;
			
		case WATER:
		 	if (defendType == Type.WATER || defendType == Type.GRASS) {
				effectiveness = NOT_EFFECTIVE;
			} else if (defendType == Type.FIRE) {
				effectiveness = SUPER_EFFECTIVE;
			}
		 	break;
			
		case ELECTRIC:
		 	if (defendType == Type.ELECTRIC || defendType == Type.GRASS) {
				effectiveness = NOT_EFFECTIVE;
			} else if (defendType == Type.WATER) {
				effectiveness = SUPER_EFFECTIVE;
			}
		 	break;
			
		case GRASS:
		 	if (defendType == Type.FIRE || defendType == Type.GRASS) {
				effectiveness = NOT_EFFECTIVE;
			} else if (defendType == Type.WATER) {
				effectiveness = SUPER_EFFECTIVE;
			}
		 	break;
		}
	
		return effectiveness;
	}
}
