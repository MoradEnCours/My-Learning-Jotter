package superhero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Represents a player in the video game, who has a set of characters available.
 * 
 * @author mefos
 *
 */
public class Player {
	private Set<GameCharacter> characters;

	public Player(Set<GameCharacter> characters) {
		this.characters = characters;
	}

	/**
	 * @return the characters
	 */
	public Set<GameCharacter> getCharacters() {
		return characters;
	}

	/**
	 * Given a set of powers, this method returns the characters required to provide all
	 * of the requested powers. If no characters can be found to cover all of the
	 * required powers, this method returns null. If more than one group of
	 * characters can provide the required powers, any valid group can be returned.
	 * 
	 * @param neededPowers The powers required
	 * @return A list of characters providing the powers, or null if no such set can be found
	 */
	public List<GameCharacter> chooseCharacters(Power[] neededPowers) {
		// Keep track of what powers we need and check them off when found
		Map<Power, Boolean> checkList = new HashMap<>();
		for (Power p : neededPowers) {
			checkList.put(p, false);
		}
		
		// Store the chosen characters
		List<GameCharacter> chosen = new ArrayList<>();
		
		// Check each character one at a time
		for (GameCharacter gc : characters) {
			// Does this character have a power we still need?
			for (Power p : gc.getPowers()) {
				// The one and only time that comparing to false with "==" makes sense
				// (because it could potentially be null)
				if (checkList.get(p) == Boolean.FALSE) {
					// Include this character in the list, and check off *all* of their powers
					chosen.add(gc);
					for (Power p2 : gc.getPowers()) {
						if (checkList.containsKey(p2)) {
							checkList.put(p2, true);
						}
					}
					// Don't need to continue checking this character, we already know we need them
					break;
				}
			}
		}
		
		// Now we need to see if there are any powers we didn't find
		
		// Non-fancy version (without streams)
		boolean done = true;
		for (Power p : checkList.keySet()) {
			if (!checkList.get(p)) {
				done = false;
				break;
			}
		}
		
		// Fancy version (with streams) -- note annoying need to un-box from Boolean to boolean
		// boolean done = checkList.values().stream().noneMatch(b -> !b.booleanValue());
		
		// If we found all the powers we need, return the characters.
		// If some power(s) still missing, return null
		if (done) {
			return chosen;
		} else {
			return null;
		}
	}
	
	/**
	 * Main method, for testing. Not required, but probably useful, and you won't be deducted
	 * if you submit files containing a main method like this.
	 */
	public static void main(String[] args) {
		// Create the characters from the example
		Set<GameCharacter> characters = new HashSet<>();
		characters.add(new GameCharacter("Robin", new Power[] { Power.WEAPONS }));
		characters.add(new GameCharacter("Starfire", new Power[] { Power.FLIGHT, Power.ENERGY_BLAST }));
		characters.add(new GameCharacter("Cyborg", new Power[] { Power.STRENGTH, Power.WEAPONS }));
		characters.add(new GameCharacter("Beast Boy", new Power[] { Power.TRANSFORMATION }));
		characters.add(new GameCharacter("Raven", new Power[] { Power.MAGIC }));
		
		// Make a player containing those characters
		Player player = new Player(characters);
		
		// Do the tests from the lab sheet
		System.out.println(player.chooseCharacters(new Power[] { Power.WEAPONS, Power.STRENGTH }));
		System.out.println(player.chooseCharacters(new Power[] { Power.FLIGHT, Power.STRENGTH, Power.TRANSFORMATION }));
		System.out.println(player.chooseCharacters(new Power[] { Power.WEAPONS, Power.MAGIC }));
		System.out.println(player.chooseCharacters(new Power[] { Power.TRANSFORMATION, Power.MAGIC, Power.SCIENCE }));
	}

}
