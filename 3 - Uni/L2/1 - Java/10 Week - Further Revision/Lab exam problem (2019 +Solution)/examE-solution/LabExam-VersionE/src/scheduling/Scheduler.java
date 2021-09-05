package scheduling;

import java.util.Set;

/**
 * An instance of the scheduling problem: stores the sets of users and time
 * slots, and chooses the best slot based on the votes.
 * 
 * Sample solution to JP2 Lab Exam 2019 (version E).
 * 
 * @author mefoster
 */
public class Scheduler {

	/** The set of time slots */
	private Set<TimeSlot> timeSlots;
	/** The set of users */
	private Set<String> users;

	/**
	 * @param timeSlots
	 * @param users
	 */
	public Scheduler(Set<TimeSlot> timeSlots, Set<String> users) {
		this.timeSlots = timeSlots;
		this.users = users;
	}

	/**
	 * Chooses the best time slot based on the votes -- i.e., the slot that the most
	 * users have voted for. If there is more than one possible "best" time slot, it
	 * returns one of them. This can consider "if-need-be" votes to be either "yes"
	 * or "no" votes depending on the setting of the flag.
	 * 
	 * @param ifNeedBe If true, treats "if-need-be" votes as "yes"; if false,
	 *                 treates "if-need-be" votes as "no"
	 * @return A time slot where the maximum number of people can come
	 */
	public TimeSlot chooseTimeSlot(boolean ifNeedBe) {
		int bestCount = -1;
		TimeSlot bestSlot = null;
		for (TimeSlot ts : timeSlots) {
			int count = 0;
			for (String user : users) {
				switch (ts.getVote(user)) {
				case YES:
					count++;
					break;
				case IF_NEED_BE:
					if (ifNeedBe) {
						count++;
					}
					break;
				case NO:
					// Do nothing
				}
			}
			if (count > bestCount) {
				bestCount = count;
				bestSlot = ts;
			}
		}
		return bestSlot;
	}

}
