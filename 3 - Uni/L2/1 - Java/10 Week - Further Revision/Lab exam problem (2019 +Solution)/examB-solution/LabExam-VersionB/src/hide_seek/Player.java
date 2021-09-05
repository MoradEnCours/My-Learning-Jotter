package hide_seek;

import java.util.Objects;

/**
 * A player in the hide-and-seek game.
 * 
 * Sample solution to JP2 Lab Exam 2019 (version B).
 * 
 * @author mefoster
 */
public class Player {
	
	/** The player's name */
	private String name;
	/** Whether the player is active */
	private boolean active;
	/** Whether this player is a seeker */
	private boolean seeker;
	/** This player's current location */
	protected Location location;
	
	/**
	 * Creates a new game player. The player will be set to active, and the location will
	 * be chosen at random.
	 * 
	 * @param name The player's name
	 * @param seeker Whether this player is a seeker
	 */
	public Player(String name, boolean seeker) {
		this.name = name;
		this.seeker = seeker;
		this.active = true;
		this.location = Location.chooseRandomLocation();
	}
	
	/**
	 * @return Whether the player is active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active The active flag to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return Whether the player is a seeker
	 */
	public boolean isSeeker() {
		return seeker;
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, location, name, seeker);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return active == other.active && location == other.location && Objects.equals(name, other.name)
				&& seeker == other.seeker;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", active=" + active + ", seeker=" + seeker + ", location=" + location + "]";
	}

}
