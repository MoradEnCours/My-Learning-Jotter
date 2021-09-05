package flights;

import java.util.Objects;

/**
 * A single flight in the frequent-flier programme -- can be bought and taken.
 * 
 * Sample solution to JP2 Lab Exam 2019 (version D).
 * 
 * @author mefoster
 */
public class Flight {

	/** The origin airport of the flight */
	private String origin;
	/** The destination airport of the flight */
	private String destination;
	/** How much it costs to purchase the flight in miles */
	private int priceInMiles;
	/** How many miles will be earned by taking the flight */
	private int milesEarned;

	/**
	 * Creates a new Flight with the given properties.
	 * 
	 * @param origin The origin to use
	 * @param destination The destination to use
	 * @param priceInMiles The price in miles
	 * @param milesEarned The number of miles earned
	 */
	public Flight(String origin, String destination, int priceInMiles, int milesEarned) {
		this.origin = origin;
		this.destination = destination;
		this.priceInMiles = priceInMiles;
		this.milesEarned = milesEarned;
	}

	/**
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * @return the priceInMiles
	 */
	public int getPriceInMiles() {
		return priceInMiles;
	}

	/**
	 * @return the milesEarned
	 */
	public int getMilesEarned() {
		return milesEarned;
	}

	@Override
	public int hashCode() {
		return Objects.hash(destination, milesEarned, origin, priceInMiles);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		return Objects.equals(destination, other.destination) && milesEarned == other.milesEarned
				&& Objects.equals(origin, other.origin) && priceInMiles == other.priceInMiles;
	}

	@Override
	public String toString() {
		return "Flight [origin=" + origin + ", destination=" + destination + ", priceInMiles=" + priceInMiles
				+ ", milesEarned=" + milesEarned + "]";
	}
}
