package planning;

import java.util.Objects;

/**
 * An object in a planning problem. Has a name and a location. Called Thing because
 * creating a class called Object is logistically very annoying.
 * 
 * Sample solution to JP2 Lab Exam 2019 (version C).
 * 
 * @author mefoster
 */
public class Thing {
	/** The name of the object */
	private String name;
	/** The object's current location */
	private Location location;
	
	/**
	 * Creates a new Thing with the given name and location. 
	 * 
	 * @param name The name of the object
	 * @param location The initial location
	 */
	public Thing(String name, Location location) {
		this.name = name;
		this.location = location;
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

	@Override
	public String toString() {
		return "Thing [name=" + name + ", location=" + location + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(location, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Thing other = (Thing) obj;
		return location == other.location && Objects.equals(name, other.name);
	}
	
}
