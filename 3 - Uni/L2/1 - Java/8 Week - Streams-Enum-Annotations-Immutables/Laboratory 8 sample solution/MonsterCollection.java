package monster;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a collection of monsters. The collection itself is immutable,
 * although note that this implementation does not prevent a user from modifying
 * the monsters inside the collection
 * 
 * @author mefos
 */
public final class MonsterCollection {
	private final Set<Monster> monsters;
	
	/**
	 * Creates a new MonsterCollection based on the given set.
	 * 
	 * @param monsters The monsters to include
	 */
	public MonsterCollection(Set<Monster> monsters) {
		this.monsters = new HashSet<>(monsters);
	}
	
	/**
	 * Returns all monsters in the collection.
	 * 
	 * @return All of the monsters
	 */
	public Set<Monster> getMonsters() {
		return monsters.stream().collect(Collectors.toSet());
	}
	
	/**
	 * Returns all monsters in the collection of a given type.
	 * 
	 * @param type The type to check for
	 * @return The monsters that have that type as one of their types
	 */
	public Set<Monster> getMonstersOfType(Type type) {
		return monsters.stream()
				.filter(m -> m.hasType(type))
				.collect(Collectors.toSet());
	}
	
	/**
	 * Returns the monster in the collection with the highest HP.
	 * 
	 * @return The strongest monster, or null if the collection is empty
	 */
	public Monster getStrongestMonster() {
		return monsters.stream().sorted().findFirst().orElse(null);
	}
	
	/**
	 * Returns a randomly-chosen monster with non-zero HP, for battling.
	 * 
	 * @return A monster from the collection with non-zero HP, or null if there is no such monster
	 */
	public Monster chooseBattleMonster() {
		return monsters.stream().filter(m -> !m.isFainted()).findAny().orElse(null);
	}
	
	/**
	 * Computes and returns the average HP of all monsters in the collection.
	 * 
	 * @return The average HP, or 0 if the collection is empty
	 */
	public double getAverageHP() {
		return monsters.stream()
				.mapToDouble(m -> m.getHP())
				.average()
				.orElse(0);
	}

}
