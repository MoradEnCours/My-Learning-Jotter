package superhero;

import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a character in a video game. Each character has a name
 * and a set of powers.
 * 
 * @author mefos
 *
 */
public class GameCharacter {
	private String name;
	private Power[] powers;
	
	public GameCharacter(String name, Power[] powers) {
		this.name = name;
		this.powers = powers;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the powers
	 */
	public Power[] getPowers() {
		return powers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(powers);
		result = prime * result + Objects.hash(name);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof GameCharacter)) {
			return false;
		}
		GameCharacter other = (GameCharacter) obj;
		return Objects.equals(name, other.name) && Arrays.equals(powers, other.powers);
	}

	@Override
	public String toString() {
		return "GameCharacter [name=" + name + ", powers=" + Arrays.toString(powers) + "]";
	}
	
}
