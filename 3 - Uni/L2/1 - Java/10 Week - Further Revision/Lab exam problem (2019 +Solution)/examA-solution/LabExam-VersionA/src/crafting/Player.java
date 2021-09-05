package crafting;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * A player of the crafting game.
 * 
 * Sample solution to JP2 Lab Exam 2019 (version A).
 * 
 * @author mefoster
 */
public class Player {
	/** The player's name */
	private String name;
	/** The resources in the inventory */
	private Map<Resource, Integer> inventory;
	
	/**
	 * Creates a new Player with the given name and inventory.
	 * 
	 * @param name The player's name to use
	 * @param inventory The inventory to use
	 */
	public Player(String name, Map<Resource, Integer> inventory) {
		this.name = name;
		this.inventory = inventory;
	}

	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @return the inventory
	 */
	public Map<Resource, Integer> getInventory() {
		return inventory;
	}


	
	@Override
	public int hashCode() {
		return Objects.hash(inventory, name);
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
		return Objects.equals(inventory, other.inventory) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", inventory=" + inventory + "]";
	}

	/**
	 * Crafts the given resource if possible, using the given set of recipes. Returns true
	 * if the crafting attempt succeeds, and false if it fails
	 * 
	 * @param result The resource to craft
	 * @param recipes The recipes to use
	 * @return True if the crafting was successful, and false if it fails
	 */
	public boolean craft(Resource result, Set<Recipe> recipes) {
		for (Recipe recipe : recipes) {
			if (recipe.getResult() == result) {
				for (Resource required : recipe.getIngredients().keySet()) {
					int numRequired = recipe.getIngredients().get(required);
					if (!inventory.containsKey(required)) {
						return false;
					} else if (inventory.get(required) < numRequired) {
						return false;
					}
				}
				// If we get here we know that crafting can work
				for (Resource required : recipe.getIngredients().keySet()) {
					int newNumber = inventory.get(required) - recipe.getIngredients().get(required);
					inventory.put(required, newNumber);
				}
				// How many of the newly crafted thing did we have before? Add one to it
				Integer oldCrafted = inventory.get(result);
				if (oldCrafted == null) {
					oldCrafted = 0;
				}
				inventory.put(result, oldCrafted + 1);
				return true;
			}
		}
		return false;
	}
}
