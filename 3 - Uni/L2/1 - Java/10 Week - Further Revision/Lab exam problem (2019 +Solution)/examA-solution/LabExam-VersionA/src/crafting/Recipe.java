package crafting;

import java.util.Map;

/**
 * Represents a crafting recipe from the crafting game.
 * 
 * Sample solution to JP2 Lab Exam 2019 (version A).
 * 
 * @author mefoster
 */
public class Recipe {
	/** The ingredients to use */
	private Map<Resource, Integer> ingredients;
	/** The intended result */
	private Resource result;
	
	/**
	 * Creates a new Recipe with the given parameters.
	 * 
	 * @param result The intended result
	 * @param ingredients The ingredients to use
	 */
	public Recipe(Resource result, Map<Resource, Integer> ingredients) {
		this.result = result;
		this.ingredients = ingredients;
	}
	
	/**
	 * @return the result
	 */
	public Resource getResult() {
		return result;
	}
	
	/**
	 * @return the ingredients
	 */
	public Map<Resource, Integer> getIngredients() {
		return ingredients;
	}
	
}