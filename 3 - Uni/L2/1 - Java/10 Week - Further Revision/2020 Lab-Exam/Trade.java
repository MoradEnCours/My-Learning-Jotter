package trading;

import java.util.Objects;

/**
 * Represents a single Trade 
 * 
 * @author Morad
 */
public class Trade {
	/* The number of gems involved in the trade */
	private int gems;
	/* The amount of goods involved in the trade */
	private int amount;
	/* The type of goods involved in the trade */
	private Goods goods;
	
	/**
	 * Creates a new trade with the given parameters
	 * @param gems
	 * @param amount
	 * @param goods
	 */
	public Trade(int gems, int amount, Goods goods) {
		this.gems = gems;
		this.amount = amount;
		this.goods = goods;
	}

	/**
	 * @return the gems
	 */
	public int getGems() {
		return gems;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @return the goods
	 */
	public Goods getGoods() {
		return goods;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, gems, goods);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Trade)) {
			return false;
		}
		Trade other = (Trade) obj;
		return amount == other.amount && gems == other.gems && goods == other.goods;
	}

	@Override
	public String toString() {
		//return "Trade [gems=" + gems + ", amount=" + amount + ", goods=" + goods + "]";
		return gems + " gem for " + amount + " " + goods;
	}
	
	/* Executes a trade exchange between a Trader and a Citizen 
	 * Throws an illegal argument if the trade is not supported
	 * Currently the method is not implemented correctly
	 * 
	 * @param trader The trader involved
	 * @param citizen The citizen involved
	 */
	public void execute(Trader trader, Citizen citizen) {
		if (trader.getTrades().contains(Trade)) {
			if (citizen.executeTrade(Trade)) {
				trader.addRandomTrade();
			}
		} else {
			throw new IllegalArgumentException("The trade is not already included in the list of trades supported by the trader");
		}
		
	}
	
}
