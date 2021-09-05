package scheduling;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a single time slot in the scheduling problem: a date and time,
 * as well as a set of user votes.
 * 
 * Sample solution to JP2 Lab Exam 2019 (version E).
 * 
 * @author mefoster
 */
public class TimeSlot {

	/** The date of the time slot */
	private String date;
	/** The time of the time slot */
	private String time;
	/** The set of user responses to the proposed time slot */
	private Map<String, UserResponse> userResponses;

	
	/**
	 * Creates a new TimeSlot representing the given date and time, with an empty set of
	 * votes.
	 * 
	 * @param date The date
	 * @param time The time
	 */
	public TimeSlot(String date, String time) {
		this.date = date;
		this.time = time;
		this.userResponses = new HashMap<String, UserResponse>();
	}
	
	/** 
	 * Stores the vote for the given user for this time slot.
	 * 
	 * @param user The user
	 * @param response The vote for this time slot
	 */
	public void setVote(String user, UserResponse response) {
		userResponses.put(user, response);
	}
	
	/**
	 * Returns the vote for the given user for this time slot.
	 * 
	 * @param user The user to look for
	 * @return The user's vote, or null if they have not yet voted
	 */
	public UserResponse getVote(String user) {
		return userResponses.get(user);
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, time, userResponses);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeSlot other = (TimeSlot) obj;
		return Objects.equals(date, other.date) && Objects.equals(time, other.time)
				&& Objects.equals(userResponses, other.userResponses);
	}

	@Override
	public String toString() {
		return "TimeSlot [date=" + date + ", time=" + time + ", userResponses=" + userResponses + "]";
	}
}
