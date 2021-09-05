package monster;

/**
 * Represents an item that may have one or more types.
 *
 * @author mefos
 */
public interface TypedItem {
	/**
	 * Checks whether the given item has the given type
	 * @param type the type to check
	 * @return Whether this item has the given type
	 */
	public boolean hasType (Type type);
	
	
	/**
	 * Returns the list of types of this TypedItem.
	 * 
	 * @return All types associated with this TypedItem
	 */
	public Type[] getTypes();
}
