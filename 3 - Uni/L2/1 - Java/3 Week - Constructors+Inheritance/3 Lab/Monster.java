public class Monster {

	
	private String name;							
	private String type1;						
	private String type2;
	private Move moveArray[] = new Move[4];				//Create an object array, max length of 4
	
	public Monster(String name, String type)		//Constructor A: For monsters of only one type
	{
		this.name = name;
		this.type1 = type;		
	}
	
	
	public Monster(String name, String type1, String type2)		//Constructor B: For monsters of two types
	{
		this.name = name;
		this.type1 = type1;
		this.type2 = type2;
	}
	
	
	public String getName()					//Getter method to obtain name of monster
	{
		return this.name;
	}
	
	
	
	public Boolean hasType(String type)					// Method to check if Monster has type equal to what is in the parameter
	{
		return (type.equals(type1) || type.equals(type2));
	}
	
			// Getter method to return move at indexed parameter position
	public Move getMove(int index)
	{
		return moveArray[index];
	}
	
			// Setter method to set move at indexed parameter position
	public void setMove(int index, Move move)
	{
		moveArray[index] = move;
	}
	
		
	public String toString()
	{
		if (type2 == null)
			return name + " [" + type1 + "]";
		else
			return name + " [" + type1 + ", " + type2 + "]";
	}
	

}
