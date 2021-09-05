package trading;

import java.util.*;

/*
 * Represents a citizen in the game
 * 
 * @author Morad
 */
public class Citizen {
	private int gems;
	private Map<Goods, Integer> inventory;
	
	/**
	 * Creates a new citizen with the specified number of gems and an empty inventory
	 * 
	 * @param gems
	 */
	public Citizen(int gems) {
		this.gems = gems;
		this.inventory = new HashMap<>();
		
	}
	/**
	 * Gets the number of gems the citizen has currently
	 * @return the gems
	 */
	public int getGems() {
		return gems;
	}
	
	/*
	 * Returns the current amount of the indicated Goods type in the inventory.
	 * Returns 0 if the Citizen does not have any of the indicated Goods
	 * 
	 * @param goods The goods whose amount is to be searched for.
	 * 
	 * @return the amount of the goods possessed
	 */
	public int getAmount(Goods goods) {
		if (!inventory.containsKey(goods)) {
			return 0;
		}
		return inventory.get(goods);
	}
	
	/* Checks whether the given trade is possible 
	 * (i.e., whether the Citizen has enough gems) and executes it if it is
	 * 
	 * @param trade The trade which is wanted to be executed
	 * 
	 * @return true if the trade is successful with updates made to gems and inventory
	 * @return false if the trade cannot be executed due to insufficient gems
	 */
	public boolean executeTrade(Trade trade) {
		if (gems < trade.getGems()) {
			return false;			
		} else {
			gems -= trade.getGems();
			Integer previouslyExisted = inventory.get(trade.getGoods());
			if (previouslyExisted == null) {
				previouslyExisted = 0;
			}
			inventory.put(trade.getGoods(), previouslyExisted + trade.getAmount());
			return true;
		}
	}

}
