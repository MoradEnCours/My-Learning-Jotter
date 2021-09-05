package flights;

/**
 * The possible levels in a frequent-flier programme. This version includes the
 * level-up value as a field in the enumeration; it would also be valid to include
 * this information and logic in the Passenger class.
 * 
 * Sample solution to JP2 Lab Exam 2019 (version D).
 * 
 * @author mefoster
 */
public enum Rank {
	BASIC(0), BRONZE(100), SILVER(500), GOLD(1000);
	
	/** The number of miles required to achieve the given rank. */
	public final int milesRequired;
	
	private Rank(int milesRequired) {
		this.milesRequired = milesRequired;
	}
}
