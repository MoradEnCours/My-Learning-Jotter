package flights;

import java.util.List;

/**
 * A member in the frequent-flier program. Has a name, a rank, a number of miles, and a list of
 * upcoming flights. A member take a flight (if the flight list is not empty), and can buy new
 * flights and level up (if they have enough miles).
 * 
 * Sample solution to JP2 Lab Exam 2019 (version D).
 * 
 * @author mefoster
 */
public class Member {
	/** The member's name (unused according to the spec -- this annotation is not required but removes the warning) */
	@SuppressWarnings("unused")
	private String name;
	/** The member's current rank in the programme */
	private Rank rank;
	/** The member's current miles balance */
	private int numMiles;
	/** The list of upcoming flights that the member has booked */
	private List<Flight> upcomingFlights;
	
	/**
	 * Creates a new Member with the given parameters.
	 * 
	 * @param name The passenger's name
	 * @param rank The current rank
	 * @param numMiles The miles balance
	 * @param upcomingFlights The list of booked flights
	 */
	public Member(String name, Rank rank, int numMiles, List<Flight> upcomingFlights) {
		this.name = name;
		this.rank = rank;
		this.numMiles = numMiles;
		this.upcomingFlights = upcomingFlights;
	}

	/**
	 * Purchases the new flight if the miles balance is enough, or throws an exception if
	 * there are not enough miles.
	 * 
	 * @param flight The flight to purchase
	 * @throws IllegalArgumentException If the member does not have enough miles to purchase the flight
	 */
	public void purchaseFlight(Flight flight) {
		if (flight.getPriceInMiles() > numMiles) {
			throw new IllegalArgumentException("Not enough miles!");
		}
		numMiles -= flight.getPriceInMiles();
		upcomingFlights.add(flight);
	}
	
	/**
	 * Records that the next flight in the list has been taken. The flight is removed from
	 * the list of pending flights, and the miles earned are added to the balance.
	 */
	public void takeNextFlight() {
		if (!upcomingFlights.isEmpty()) {
			Flight f = upcomingFlights.remove(0);
			numMiles += f.getMilesEarned();
		}
	}
	
	/**
	 * If possible, moves the user up to the next level in the frequent-flier programme.
	 * This is possible if (a) the user isn't already at the maximum level, and (b) if
	 * they have enough miles to move up.
	 */
	public void rankUp() {
		if (rank.ordinal() == Rank.values().length - 1) {
			return;
		}
		Rank nextRank = Rank.values()[rank.ordinal() + 1];
		if (numMiles >= nextRank.milesRequired) {
			this.rank = nextRank;
			this.numMiles -= nextRank.milesRequired;
		}
	}		
}
