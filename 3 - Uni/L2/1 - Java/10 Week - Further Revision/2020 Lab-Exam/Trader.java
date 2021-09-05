package trading;

import java.util.*;
import java.util.Random;

/* Represents a trader in the world
 * 
 * @author Morad
 */
public class Trader {
	private List<Trade> trades = new ArrayList<>();
	private Random rand = new Random();
	private Trade randomTrade;
		

	/**
	 * Creates a trader with a single, randomly-chosen trade
	 */
	public Trader() {
		this.randomTrade = new Trade(rand.nextInt(), rand.nextInt(), chooseGoods());
		this.trades.add(randomTrade);
	}
	
	/* 
	 * Returns the current list of Trades supported by this Trader
	 * 
	 * @return trades
	 */
	public List<Trade> getTrades() {
		return trades;
	}
	
	/* 
	 * Adds a new, randomly-chosen Trade to the list with constraints applied
	 */
	public void addRandomTrade() {
		trades.add(new Trade(rand.nextInt((5-1)+1)+1, rand.nextInt((5-1)+1)+1, chooseGoods()));
	}
	
	/*
	 * Helper method to obtain random goods for constructing random trade to implement
	 * 
	 * @return a random Goods
	 */
	private Goods chooseGoods() {
		return Goods.chooseRandomGoods();
	}
	

}
