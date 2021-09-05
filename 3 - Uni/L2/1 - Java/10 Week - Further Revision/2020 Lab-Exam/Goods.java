package trading;

import java.util.Random;

/**
 * The possible goods that can be used in the trade exchange.
 * 
 * Final Exam solution to 2020 Lab exam
 * 
 * @author Morad
 */
public enum Goods {
	BREAD, COAL, FISH, HELMET, IRON, PAPER, SHIELD, SWORD, WOOD, WOOL;
	
	private static final Random RAND = new Random();
	
	public static Goods chooseRandomGoods() {
		return values()[RAND.nextInt(values().length)];
	}
}
