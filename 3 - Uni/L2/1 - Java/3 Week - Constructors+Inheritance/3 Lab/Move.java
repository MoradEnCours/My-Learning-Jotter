public class Move {

		// Fields
    private String name;
    private String type;
    private int power;
    
		// Constructor
    public Move(String name, String type, int power)
    {
    	this.name = name;
    	this.type = type;
    	this.power = power;
    }
    
		// Getters for name, type and power
    public String getName()
    {
    	return this.name;
    }
    
    
    public String getType()
    {
    	return this.type;
    }
    
    
    public int getPower()
    {
    	return this.power;
    }
    
    
    
		// Readable representation
    public String toString()
    {
    	return name + " (" + type + "): " + power;
    }
	
    
        
}
