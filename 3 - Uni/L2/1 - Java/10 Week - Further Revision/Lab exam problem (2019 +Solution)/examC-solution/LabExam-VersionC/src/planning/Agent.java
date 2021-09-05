package planning;

import java.util.Random;
import java.util.Set;

/**
 * An unreliable agent in a planning problem. Provides methods to pick up and put
 * down objects, as well as a method to use those to move objects to the table.
 * 
 * Sample solution to JP2 Lab Exam 2019 (version C).
 * 
 * @author mefoster
 */
public class Agent {
	
	/** Used internally to implement the unreliability */
	private Random rand = new Random();
	/** The object that the agent is currently holding */
	private Thing holding = null;
	
	/**
	 * Attempts to pick up the given object. Returns true if the pick-up was successful,
	 * and false if it failed. A pick-up can fail for two reasons:
	 * 1. If the agent is already holding something else
	 * 2. If the attempted pick-up fails
	 * 
	 * @param thing The object to pick up.
	 * @return True if the object was picked up, and false if not
	 */
	public boolean pickUp(Thing thing) {
		if (holding != null) {
			return false;
		}
		if (rand.nextBoolean()) {
			thing.setLocation(Location.HOLDING);
			holding = thing;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Puts down the object that the agent is currently holding at the given location, and
	 * returns a flag indicating if the action was successful. A put-down action can fail because:
	 * 1. The agent isn't holding anything
	 * 2. The target location is HOLDING
	 * 3. The action fails randomly
	 * @param place The place to put the object
	 * @return True if it succeeds, and false if it fails
	 */
	public boolean putDown(Location place) {
		if (holding == null) {
			return false;
		}
		if (place == Location.HOLDING) {
			return false;
		}
		if (rand.nextBoolean()) {
			holding.setLocation(place);
			holding = null;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Ensures that all objects are on the table, using the pickUp() and putDown() methods.
	 * 
	 * @param things The objects to move
	 */
	public void putOnTable(Set<Thing> things) {
		// First, put down the object that we might be holding (if any) or else
		// we can't pick up anything else
		if (holding != null) {
			// Horribly ugly syntax to try putting it down until it works
			while (putDown(Location.TABLE));
		}
		
		// Now move all of the other objects to the table
		for (Thing thing : things) {
			// Only move objects that are not already on the table
			if (thing.getLocation() != Location.TABLE) {
				// Keep trying until you succeed in picking it up, and then keep trying
				// until you put it down
				// Terrible syntax here again but it works :)
				while (pickUp(thing));
				while (putDown(Location.TABLE));
			}
		}
	}

}
